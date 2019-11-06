package com.smart;

import com.smart.db.dao.MplMenuDAO;
import com.smart.db.dao.MplRoleDAO;
import com.smart.db.model.MplRole;
import com.smart.web.util.SmartUtil;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.alibaba.druid.pool.DruidAbstractDataSource;
import com.alibaba.druid.pool.DruidConnectionHolder;
import com.alibaba.druid.pool.DruidDataSource;
import com.smart.db.dao.MplBtuDAO;
import com.smart.db.model.MplBtu;
import java.util.List;
import lombok.experimental.Accessors;
import org.apache.commons.beanutils.ConvertUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.sql.DataSource;

@MapperScan({"com.smart.db.dao"})
@RunWith(SpringRunner.class)
@SpringBootTest
public class SmartApplicationTests {

    @Autowired
    WebApplicationContext context;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    private MplRoleDAO roleDAO;
    @Autowired
    ApplicationContext applicationContext;
    /**
     * 生成离线swaggerJson
     */
    @Test
    public void swithDataSource() {
        getSwaggerJson();
    }


    @Autowired
    private MplBtuDAO mplBtuDAO;


    @Autowired
    MplMenuDAO mplMenuDAO;





    @Test
    public void testSql(){
    /*    DruidDataSource  dataSource =   (DruidDataSource)applicationContext.getBean("dataSource");
        System.out.println(dataSource.getPassword());*/
        DataSource d=  applicationContext.getBean(DataSource.class);
        //jdbc:mysql://localhost:3306/smart?useUnicode=true&characterEncoding=utf8
       // WebApplicationContext wac = (WebApplicationContext) request.getSession().getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
/*
       MplBtu mplBtu= mplBtuDAO.selectByPrimaryKey(2);
        System.out.println(mplBtu.getBtuName());*/
    }
    @Test
    public void getSwaggerJson() {



        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/v2/api-docs").accept(MediaType.APPLICATION_JSON))
                    .andDo((result) -> {
                        Path path = Paths.get("./swagger.json");
                        Files.write(path, result.getResponse().getContentAsByteArray());
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRedis() {
        String ip = "1880188801";
        HashOperations hashOperations1=  redisTemplate.opsForHash();
        System.out.println(hashOperations1.hasKey("中国", "广*"));
    }


    @Test
    public void testMenu() {
        String ids ="8,9";
        //System.out.println(SmartUtil.isSucceed(mplMenuDAO.deleteByRoId(ids.split(","))));
    }



    @Test
    public void testment(){
        String[] ids = new String []{"3","4"};
        String roid = "9";
    }


}
