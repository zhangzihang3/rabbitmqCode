package com.example.zzh.service.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 动态消息路由 指定交换机的类型为topic
 * 发送消息：发送至交换机的指定的路由上面
 */
public class send {
    public static void main(String[] args) {
        String routingKey = "user.zzh";
        String exchange = "topicExchange";
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("ems");
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("ems");
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            //交换机名称、交换机类型
            channel.exchangeDeclare(exchange, "topic");
            //参数1：交换机名称 参数2：路由名称 参数3：持久化消息 参数4：消息内容
            for (int i = 0; i < 20; i++) {
                channel.basicPublish(exchange, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN, (i + "topic动态路由模型").getBytes());
            }
            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
