package com.atguigu.rabbitmq.springbootrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

//延迟
@Configuration
public class DelayedQueueConfig {

    //队列
    public static final String delayed_queue_name = "delayed.queue";

    //交换机
    public static final String delayed_exchange_name = "delayed.exchange";

    public static final String delayed_routing_key = "delayed.routingkey";

    @Bean
    public CustomExchange exchange(){
        Map<String,Object> map = new HashMap<>();
        map.put("x-delayed-type","direct");

        return new CustomExchange(delayed_exchange_name,"x-delayed-message",
                true,false,map);
    }


    @Bean
    public Queue queue(){
        return new Queue(delayed_queue_name);
    }


    @Bean
    public Binding binding(@Qualifier("exchange") CustomExchange e,@Qualifier("queue") Queue q){
        return  BindingBuilder.bind(q).to(e).with(delayed_routing_key).noargs();
    }
}
