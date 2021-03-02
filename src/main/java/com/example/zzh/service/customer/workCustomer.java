package com.example.zzh.service.customer;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * work路由模型：发送消息指定队列即可
 */
@Component
public class workCustomer {
    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void receivel(String msg) {
        System.out.println("work消费者一msg:" + msg);
    }
    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void receivel2(String msg) {
        System.out.println("work消费者二msg:" + msg);
}
}
