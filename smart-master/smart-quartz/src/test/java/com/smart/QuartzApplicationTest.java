package com.smart;

import com.smart.quartz.job.HelloJob;
import com.smart.quartz.util.ScheduleUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.quartz.Scheduler;
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
    @Autowired
    private Scheduler scheduler;

    @Autowired
    private HelloJob helloJob;


    /**
     * 测试
     */
    @Test
    public void test() {
        try {
            scheduler.deleteJob(ScheduleUtils.getJobKey(10L, "一组"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    /**
     * 测试
     */
    @Test
    public void testInitMysql() {
        try {
            helloJob.initMySql();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
