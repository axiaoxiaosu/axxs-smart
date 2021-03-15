package com.smart.quartz.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

/**
 * @author lwq
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Value("${spring.jackson.date-format}")
    public String dateFormat;
    @Value("${spring.jackson.time-zone}")
    public TimeZone timeZone;
    @Autowired
    TimeInter timeInter;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInter);
    }

    /**
     * date fromat转换 将所有的date转换展示
     */
    @Bean
    public MappingJackson2HttpMessageConverter jackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setDateFormat(new SimpleDateFormat(dateFormat));
        mapper.setTimeZone(timeZone);
        converter.setObjectMapper(mapper);
        return converter;
    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(jackson2HttpMessageConverter());
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
