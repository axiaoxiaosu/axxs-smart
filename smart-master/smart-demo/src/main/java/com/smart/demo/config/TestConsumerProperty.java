package com.smart.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "smart.consumer.test")
public class TestConsumerProperty {
    private String topic;
}
