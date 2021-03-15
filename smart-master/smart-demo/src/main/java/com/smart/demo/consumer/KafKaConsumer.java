//package com.smart.demo.consumer;
//
//import com.smart.demo.mode.Cat;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//public class KafKaConsumer {
//
//    @KafkaListener(topics = "uuu", groupId = "test-consumer")
//    public void listenTopic2(ConsumerRecord<String, Cat> consumerRecord) {
//        System.out.println("Group1收到消息：" + consumerRecord.value());
//    }
//
//}
