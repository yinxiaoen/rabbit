package com.rabbitmq.touinchaintwo;
/*
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;*/


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;


/**
 *Broker：简单来说就是消息队列服务器实体。
  Exchange：消息交换机，它指定消息按什么规则，路由到哪个队列。
  Queue：消息队列载体，每个消息都会被投入到一个或多个队列。
  Binding：绑定，它的作用就是把exchange和queue按照路由规则绑定起来。
  Routing Key：路由关键字，exchange根据这个关键字进行消息投递。
  vhost：虚拟主机，一个broker里可以开设多个vhost，用作不同用户的权限分离。
  producer：消息生产者，就是投递消息的程序。
  consumer：消息消费者，就是接受消息的程序。
  channel：消息通道，在客户端的每个连接里，可建立多个channel，每个channel代表一个会话任务。
 */
@Configuration
@RabbitListener(queues = AmqpConfig.FOO_QUEUE)
public class Listener {

    private static final Logger LOGGER = LoggerFactory.getLogger(Listener.class);

    /**
     * 设置交换机类型
     */
    @Bean
    public DirectExchange defaultExchange() {
        /**
         * DirectExchange:按照routingkey分发到指定队列
         * TopicExchange:多关键字匹配
         * FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
         * HeadersExchange ：通过添加属性key-value匹配
         */
        return new DirectExchange(AmqpConfig.FOO_EXCHANGE);
    }

    @Bean
    public Queue fooQueue() {
        return new Queue(AmqpConfig.FOO_QUEUE);
    }

    @Bean
    public Binding binding() {
        /** 将队列绑定到交换机 */
        return BindingBuilder.bind(fooQueue()).to(defaultExchange()).with(AmqpConfig.FOO_ROUTINGKEY);
    }

    @RabbitHandler
    public void process(@Payload String foo) {
        LOGGER.info("Listener: " + foo);
    }
}