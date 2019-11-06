package com.smart.quartz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;


@MapperScan({"com.smart.db.dao"})
@RunWith(SpringRunner.class)
@SpringBootTest
public class QuartzApplicationTest {

    @Autowired
    WebApplicationContext context;

    /**
     * 测试
     */
    @Test
    public void test() {

    }


}
