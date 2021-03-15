package com.smart.api.config;

import com.smart.api.filter.TimeInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.TimeZone;

/**
 * @author lwq
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${spring.jackson.date-format}")
    public String dateFormat;

    @Value("${spring.jackson.time-zone}")
    public TimeZone timeZone;
    @Autowired
    TimeInter timeInter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInter);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 参数解析
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new AuthenticationPrincipalArgumentResolver());
    }

    /**
     * 配置静态资源
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
