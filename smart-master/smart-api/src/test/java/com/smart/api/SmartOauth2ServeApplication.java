package com.smart.api;

import com.smart.api.util.CatConfigBean;
import com.smart.api.util.DogConfigBean;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@MapperScan("com.smart.db.dao")
@RunWith(SpringRunner.class)
@SpringBootTest
public class SmartOauth2ServeApplication {

    @Autowired
    WebApplicationContext context;

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    private CatConfigBean cat;
    @Autowired
    private DogConfigBean dogConfigBean;

    @Test
    public void TestCat() {
        System.out.println(dogConfigBean.getColor().toString());
        System.out.println(cat.getName());
    }


    /**
     * 生成离线swaggerJson
     */
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
}
