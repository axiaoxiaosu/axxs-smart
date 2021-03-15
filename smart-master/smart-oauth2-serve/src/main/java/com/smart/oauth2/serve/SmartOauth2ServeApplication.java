package com.smart.oauth2.serve;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.smart.db.dao")
@EnableAdminServer
@EnableSwagger2
@EnableRedisHttpSession
@EnableGlobalAuthentication
public class SmartOauth2ServeApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmartOauth2ServeApplication.class, args);
    }
}
