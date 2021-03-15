package com.smart.api.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@PropertySource(value = "classpath:cat.properties", encoding = "UTF-8")
@ConfigurationProperties(prefix = "dog")
@Component
public class DogConfigBean {
    private String name;
    private List<String> color;
    private Integer age;
}
