package com.smart.web.aspect;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.ReflectUtil;
import com.common.annotationoo.DicMark;
import com.smart.db.dao.MplDicDAO;
import com.smart.db.model.MplDic;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author lwq
 */
@Aspect
@Order(5)
@Component
@Slf4j
public class DicReplace {

    // @Pointcut("execution(public * com.smart.web.service.impl.*Impl.*(..))")

    private Set<String> strings = new HashSet<>();


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MplDicDAO mplDicDAO;

    @Pointcut("@annotation(com.common.annotationoo.SmartDic)")
    public void execute() {
    }

    /**
     * 后置
     */
    @AfterReturning(value = "execute()", returning = "retVal")
    public Object afterReturning(JoinPoint joinPoint, Object retVal) {
        strings.clear();
        if (retVal == null) {
            return null;
        }
        //对list结果集的处理
        try {
            if (retVal instanceof List) {
                ArrayList<Object> list = (ArrayList<Object>) retVal;
                for (int i = 0; i < list.size(); i++) {
                    Field[] fields = ReflectUtil.getFields(list.get(i).getClass());
                    dispose(list.get(i), fields);
                }
                //对object的处理
            } else {
                Field[] fields = retVal.getClass().getDeclaredFields();
                dispose(retVal, fields);
            }
            if (!strings.isEmpty()) {
                //使用set 不需要添加重复的异常数据
                strings.stream().forEach(e -> log.error("数据字典处理失败 【{}】", e));
            }
        } catch (Exception e) {
            log.error("字典赋值异常");
            return retVal;
        }
        return retVal;
    }

    /**
     * 考虑到在不同表中 同名字段的问题 添加tableName字段 type 1 将value 填充为text type 2 将value 填充为code
     */
    private void dispose(Object o, Field[] fields) {
        for (int i = 0; i < fields.length; i++) {
            String tableName = "";
            try {
                if (fields[i].isAnnotationPresent(DicMark.class)) {
                    Field field = fields[i];
                    Field tableNameField = o.getClass().getDeclaredField("tableName");
                    //可见性处理
                    if (!tableNameField.isAccessible()) {
                        tableNameField.setAccessible(true);
                    }
                    if (!field.isAccessible()) {
                        field.setAccessible(true);
                    }
                    //获取tableName值
                    tableName = String.valueOf(tableNameField.get(o));

                    //字段原来的值
                    String value = String.valueOf(field.get(o));

                    //先查询redis在查询db
                    HashOperations hashOperations = redisTemplate.opsForHash();
                    boolean hashkey = hashOperations.hasKey(tableName, field.getName());
                    if (hashkey) {
                        List<MplDic> mplDicList = (List<MplDic>) hashOperations.get(tableName, field.getName());
                        MplDic mplDic = mplDicList.stream().filter(e -> e.getValue().equalsIgnoreCase(value)).findFirst()
                                .orElse(null);
                        if (value != null) {
                            field.set(o, mplDic.getText());
                        }

                    } else {
                        //查询db
                        List<MplDic> mplDicList = mplDicDAO.selectByTableAndCode(tableName, field.getName());
                        if (mplDicList == null) {
                            strings.add(new StringBuffer("没有找到该字段的字典数据/n").append(tableName).append("=")
                                    .append(fields[i].getName()).toString());
                            continue;
                        }
                        MplDic mplDic = mplDicList.stream().filter(e -> e.getValue().equalsIgnoreCase(value))
                                .findFirst().get();
                        field.set(o, mplDic.getText());
                        //存储redis一份
                        hashOperations.put(tableName, field.getName(), mplDicList);
                    }
                }
            } catch (Exception e) {
                strings.add(new StringBuffer("e=>").append(ExceptionUtil.getMessage(e)).append("\n").append(tableName)
                        .append("=").append(fields[i].getName()).toString());
                continue;
            }
        }
    }
}
