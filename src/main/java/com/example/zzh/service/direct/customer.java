package com.example.zzh.service.direct;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 临时队列绑定交换机指定的路由,继而消费此队列
 */
public class customer {
    public static void main(String[] args) {
        String routingKey = "user.zzh2";
        String exchange = "directExchange";
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("ems");
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("ems");
        Connection connection = null;
        try {
            connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            String queue = channel.queueDeclare().getQueue();
            //交换机名称、交换机类型
            channel.exchangeDeclare(exchange, "direct");
            //临时队列绑定交换机指定的路由上面
            channel.queueBind(queue, exchange, routingKey);
            //参数一：消费哪个队列的消息
            //参数二：开始消息的自动确认机制
            //参数三：消费时的回掉接口
            channel.basicConsume(queue, true, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                           byte[] body) throws IOException {
                    System.out.println("direct消费者一：" + new String(body));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
}
