package com.smart.web.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author lwq
 * @data 2019/8/19
 */
@Slf4j
@Component
/*@RabbitListener(queues = "lwq_queue_wlwl")*/
public class TestConsumer extends BaseConsumer {

    /**
     * 进行消费
     */
    @RabbitHandler
    public void wlwlConsumer(@Payload String body, @Header String header) {
        log.info("消费了{}", body);
    }

}
