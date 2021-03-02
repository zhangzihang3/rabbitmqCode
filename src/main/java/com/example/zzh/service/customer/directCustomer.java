package com.example.zzh.service.customer;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * springBoot整合direct(路由)
 */
@Component
public class directCustomer {
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "directExchange",type ="direct"),
                    key = {"logs","errors"})
    })
    public void receivel(String msg) {
        System.out.println("direct消费者一msg:" + msg);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "directExchange", type = "direct"),
                    key = {"logs","warns.zzh"})
    })
    public void receivel2(String msg) {
        System.out.println("direct消费者二msg:" + msg);
    }
}
