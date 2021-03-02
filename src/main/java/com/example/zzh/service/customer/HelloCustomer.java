package com.example.zzh.service.customer;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 测试
 */
@Component
@RabbitListener(queuesToDeclare = @Queue("user"))
public class HelloCustomer {
    @RabbitHandler
    public void receive1(String msg) {
        System.out.println("msg:"+msg);
    }

}
