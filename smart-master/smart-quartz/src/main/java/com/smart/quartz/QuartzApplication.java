package com.smart.quartz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author lwq
 */
@EnableSwagger2
@ComponentScan(lazyInit = true)
@MapperScan({"com.smart.db.dao"})
@SpringBootApplication
public class QuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuartzApplication.class, args);
    }
}
