package com.smart.web.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

/**
 * @author lwq
 * @data 2019/8/20
 */
@Slf4j
public class BaseConsumer {

    @RabbitHandler(isDefault = true)
    public void defaultConsumer(@Payload String body, @Header String header) {
        log.error("消息无法被消费:body({}) header({header})", body, header);
    }
}
