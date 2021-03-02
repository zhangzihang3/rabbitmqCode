package com.example.zzh.service.customer;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * springBoot整合topic，在direct的基础上多了模糊匹配路由规则
 */
@Component
public class topicCustomer {
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "topicExchange", type = "topic"),
                    key = "user.*")
    })
    public void receivel(String msg) {
        System.out.println("topic1消费者msg:" + msg);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "topicExchange", type = "topic"),
                    key = ("user.#"))
    })
    public void receivel2(String msg) {
        System.out.println("topic2消费者msg:" + msg);
    }
}
