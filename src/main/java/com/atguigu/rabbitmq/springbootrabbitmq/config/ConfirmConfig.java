package com.atguigu.rabbitmq.springbootrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;

//发布确认高级
@Configuration
public class ConfirmConfig {

    public static final String e_exchange = "confirm.config";
    public static final String q_queue = "confirm.queue";
    public static final String routingKey = "k1";

    @Bean
    public Queue queue1(){
        return new Queue(q_queue);
    }
    @Bean
    public DirectExchange directExchange2(){
        return new DirectExchange(e_exchange);
    }

    @Bean
    public Binding binding2(@Qualifier("queue1") Queue q1,@Qualifier("directExchange2") DirectExchange e1){
        return BindingBuilder.bind(q1).to(e1).with(routingKey);


    }
}
