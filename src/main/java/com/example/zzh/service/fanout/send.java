package com.example.zzh.service.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 发送消息到交换机,消息广播 fanout
 * 场景：一条消息多个业务系统共同使用
 */
public class send {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("ems");
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("ems");
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            //交换机名字、交换机类型
            channel.exchangeDeclare("emsExchange","fanout");
            //参数一：交换机名称
            //参数二：队列名称 routing
            //参数三：持久化消息
            //参数四：消息内容
            for (int i = 0; i < 20; i++) {
                channel.basicPublish("emsExchange", "", null, (i+"fanout type message").getBytes());
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
