package com.example.zzh.service.customer;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * fanout广播：一个生产者发送消息，所有的消费者都能接受到消息
 */
@Component
public class fanoutCustomer {
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "fanoutExchange", type = "fanout"))
    })
    public void receivel(String msg) {
        System.out.println("fanout消费者1msg:" + msg);
    }
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "fanoutExchange", type = "fanout"))
    })
    public void receivel2(String msg) {
        System.out.println("fanout消费者2msg:" + msg);
    }
}
