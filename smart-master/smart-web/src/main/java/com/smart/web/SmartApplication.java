package com.smart.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author lwq
 */
@SpringBootApplication
@EnableSwagger2
@EnableWebSecurity
@EnableAsync
@ComponentScan({"com.smart.web", "com.common.interceptors", "com.smart.db.dao"})
@MapperScan({"com.smart.db.dao"})
public class SmartApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartApplication.class, args);
    }
}
