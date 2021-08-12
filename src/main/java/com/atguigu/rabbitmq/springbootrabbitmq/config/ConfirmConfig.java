package com.atguigu.rabbitmq.springbootrabbitmq.config;

import org.springframework.amqp.core.*;
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
    //备份交换机
    public static final String backup_exchange = "backup.exchange";
    //备份队列
    public static final String backup_queue = "backup.queue";
    //报警队列
    public static final String warning_queue = "warning.queue";

    @Bean
    public Queue queue1(){
        return new Queue(q_queue);
    }
    @Bean
    public DirectExchange directExchange2(){

        return ExchangeBuilder
                .directExchange(e_exchange)
                .durable(true)
                .withArgument("alternate-exchange",backup_exchange).build();
    }

    @Bean
    public Binding binding2(@Qualifier("queue1") Queue q1,@Qualifier("directExchange2") DirectExchange e1){
        return BindingBuilder.bind(q1).to(e1).with(routingKey);
    }

    @Bean
    public Queue backupQueue(){
        return new Queue(backup_queue);
    }

    @Bean
    public FanoutExchange backupDirectExchange(){
        return new FanoutExchange(backup_exchange);
    }


    @Bean
    public Queue warningQueue(){
        return new Queue(warning_queue);
    }


    @Bean
    public Binding backupBinding(@Qualifier("backupQueue") Queue q1,@Qualifier("backupDirectExchange") FanoutExchange e1){
        return BindingBuilder.bind(q1).to(e1);
    }

    @Bean
    public Binding warningBinding(@Qualifier("warningQueue") Queue q1,@Qualifier("backupDirectExchange") FanoutExchange e1){
        return BindingBuilder.bind(q1).to(e1);
    }
}
