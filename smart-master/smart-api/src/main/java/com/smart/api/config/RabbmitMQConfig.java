package com.smart.api.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lwq
 * @data 2019/8/19
 */
@Slf4j
@Configuration
public class RabbmitMQConfig {
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory factory) {
        RabbitTemplate template = new RabbitTemplate(factory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        //当没有交换机匹配到消息返回给生产者
        template.setMandatory(true);
        template.setConfirmCallback(((correlationData, ack, cause) -> log
                .info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause)));
        template.setReturnCallback(((message, replyCode, replyText, exchange, routingKey) -> log
                .error("消息丢失:message({}),replyCode({}),replyText({}),exchange({}),routingKey({})", message, replyCode,
                        replyText, exchange, routingKey)));
        return template;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory factory) {
        SimpleRabbitListenerContainerFactory containerFactory = new SimpleRabbitListenerContainerFactory();
        containerFactory.setConnectionFactory(factory);
        containerFactory.setMessageConverter(new Jackson2JsonMessageConverter());
        return containerFactory;
    }
}
