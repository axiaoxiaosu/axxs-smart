package com.smart.api.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "cat")
@PropertySource(value = "classpath:cat.properties", encoding = "UTF-8")
@Component
@Data
public class CatConfigBean {
    private String name;
    private int age;
}
