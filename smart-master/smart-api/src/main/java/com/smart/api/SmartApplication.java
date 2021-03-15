package com.smart.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author lwq
 */
@SpringBootApplication
@ComponentScan(lazyInit = true)
@EnableSwagger2
@EnableAsync
@EnableRedisHttpSession
@MapperScan("com.smart.db.dao")
public class SmartApplication {


    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SmartApplication.class);
        // app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}
