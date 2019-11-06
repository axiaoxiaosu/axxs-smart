package com.smart.quartz.config;

import com.common.interceptors.TimeInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author lwq
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Autowired
    TimeInter timeInter;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInter);
    }

    /**
     * 配置静态资源
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
