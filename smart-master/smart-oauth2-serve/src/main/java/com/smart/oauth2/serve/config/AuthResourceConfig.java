package com.smart.oauth2.serve.config;

import com.smart.oauth2.serve.handler.UserAccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;


@EnableResourceServer
@Configuration
public class AuthResourceConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private UserAccessHandler userAccessHandler;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {

        resources.accessDeniedHandler(userAccessHandler)
                .resourceId("smart-resource");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/resource/**")
                .authorizeRequests()
                .anyRequest()
                .authenticated().and()
                .exceptionHandling()
                .accessDeniedHandler(userAccessHandler);
    }
}
