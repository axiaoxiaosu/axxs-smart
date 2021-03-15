package com.smart;

import com.smart.quartz.QuartzApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lwq
 * @data 2019/9/5
 */
@SpringBootApplication
public class MainTest {

    public static void main(String[] args) {
        SpringApplication.run(QuartzApplication.class, args);
    }
}
