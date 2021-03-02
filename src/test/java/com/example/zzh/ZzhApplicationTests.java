package com.example.zzh;

import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZzhApplication.class)
class ZzhApplicationTests {
    @Autowired
    RabbitTemplate rabbitTemplate;
    /**
     * HELLO
     */
    @Test
    void contextLoads() {
        rabbitTemplate.convertAndSend("user", "hello");
    }
    /**
     * work路由模型
     */
    @Test
    void contextLoad3() {
        for (int i = 0; i <10; i++) {
            rabbitTemplate.convertAndSend("work", "work路由模型"+i);
        }
    }
    /**
     * fanout
     */
    @Test
    void contextLoads2() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("fanoutExchange", "", "fanout路由模型-" + i);
        }
    }
    /**
     * topic
     */
    @Test
    void contextLoads3() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("topicExchange", "user.zzh.zzhh", "topic路由模型-" + i);
        }
    }
    /**
     * direct
     */
    @Test
    void contextLoads4() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("directExchange", "warns.zzh", "direct路由模型-" + i);
        }
    }
}
