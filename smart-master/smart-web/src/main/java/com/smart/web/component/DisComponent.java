package com.smart.web.component;

import com.common.annotationoo.DicMark;
import com.common.exception.SmartException;
import com.smart.db.dao.MplDicDAO;
import com.smart.db.model.MplDic;
import java.lang.reflect.Field;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author lwq
 */
@Slf4j
@Component
public class DisComponent {


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MplDicDAO mplDicDAO;

    /**
     * 需要注意 当处理失败时 会终断该次操作
     */
    public <T> T dicTextToCode(T t, String tableName) {
        if (tableName == null || t == null) {
            return null;
        }
        try {
            Field[] fields = t.getClass().getDeclaredFields();
            //先查询redis在查询db
            HashOperations hashOperations = redisTemplate.opsForHash();

            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                if (field.isAnnotationPresent(DicMark.class)) {
                    boolean hashkey = hashOperations.hasKey(tableName, field.getName());
                    if (hashkey) {
                        List<MplDic> mplDicList = (List<MplDic>) hashOperations.get(tableName, field.getName());
                        field.setAccessible(true);
                        try {
                            String value = String.valueOf(field.get(t));
                            MplDic mplDic = mplDicList.stream().filter(list -> list.getText().equals(value)).findFirst().orElse(null);
                            if (mplDic != null) {
                                field.set(t, mplDic.getValue());
                            }
                        } catch (IllegalAccessException e1) {
                            log.error("处理数据字典失败");
                            log.error("处理数据字典失败--Redis");
                        }
                    } else {
                        //查询db
                        List<MplDic> mplDicList = mplDicDAO.selectByTableAndCode(tableName, field.getName());
                        if (mplDicList != null) {
                            try {
                                String value = String.valueOf(field.get(t));
                                MplDic mplDic = mplDicList.stream()
                                        .filter(list -> list.getText().equalsIgnoreCase(value))
                                        .findFirst().get();
                                if (mplDic != null) {
                                    field.set(t, mplDic.getValue());
                                }
                                //存储redis一份
                                hashOperations.put(tableName, field.getName(), mplDicList);
                            } catch (IllegalAccessException e1) {
                                log.error("处理数据字典失败--DB");
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new SmartException(300, "处理数据字典失败,请稍后重试");
        }

        return t;
    }

}
