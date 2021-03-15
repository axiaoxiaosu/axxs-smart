//package com.smart.demo.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.PropertyNamingStrategy;
//import com.smart.demo.config.TestConsumerProperty;
//import com.smart.demo.mode.Cat;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.support.SendResult;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.concurrent.ListenableFuture;
//import org.springframework.util.concurrent.ListenableFutureCallback;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.io.IOException;
//import java.util.Date;
//
//@Controller
//@Slf4j
//@RequestMapping("/demo")
//public class DemoController {
//
//    @Autowired
//    ObjectMapper mapper;
//
//
//
//    @Autowired
//    private KafkaTemplate kafkaTemplate;
//
//    @GetMapping(value = "/list")
//    @ResponseBody
//    public Cat list() throws IOException {
//        // String json = "{name:\"mrbird\",iTime:\"2018-07-12 15:49:02\"}";
//        String json = "{\"name\":\"mrbird\",\"iTime\":\"2018-06-13 20:22:00\",\"age\":26,\"password\":123}";
//        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
//        Cat cat = mapper.readValue(json, Cat.class);
//        System.out.println(cat.getAge());
//        System.out.println("你好啊");
//        return cat;
//    }
//
//    @GetMapping(value = "/send/{name}")
//    public void sendKafka(@PathVariable(value = "name") String name) {
//        Cat cat = new Cat();
//        cat.setITime(new Date());
//        cat.setName(name);
//        cat.setAge(3);
//        ListenableFuture<SendResult<String, Cat>> listenableFuture = kafkaTemplate.send("uuu", cat);
//        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, Cat>>() {
//            @Override
//            public void onFailure(Throwable ex) {
//                log.error("消息发送失败,失败原因:{}", ex.getMessage());
//            }
//
//            @Override
//            public void onSuccess(SendResult<String, Cat> result) {
//                log.info("消息发送成功");
//            }
//        });
//    }
//
//}
