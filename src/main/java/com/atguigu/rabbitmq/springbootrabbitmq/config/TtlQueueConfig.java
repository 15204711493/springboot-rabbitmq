package com.atguigu.rabbitmq.springbootrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class TtlQueueConfig {

    //普通交换机的名称
    private static final String X_EXCHANGE = "X";
    //死信交换机的名称
    private static final String D_EXCHANGE = "Y";
    //普通队列的名称
    private static final String X_QUEUE_A = "QA";
    private static final String X_QUEUE_B = "QB";
    //死信队列的名称
    private static final String DEATH_QUEUE_D = "QD";


    //声明交换机
    @Bean("directExchangex")
    public DirectExchange directExchangex() {
        return new DirectExchange(X_EXCHANGE);
    }


    //声明交换机
    @Bean("directExchangey")
    public DirectExchange directExchangey() {
        return new DirectExchange(D_EXCHANGE);
    }

    //声明队列
    @Bean("queueA")
    public Queue queueA() {
        Map<String,Object> map = new HashMap<>();
        map.put("x-dead-letter-exchange",D_EXCHANGE);
        map.put("x-dead-letter-routing-key","YD");
        map.put("x-message-ttl",10000);
        return QueueBuilder.durable(X_QUEUE_A).withArguments(map).build();
    }

    //声明队列
    @Bean("queueB")
    public Queue queueB() {
        Map<String,Object> map = new HashMap<>();
        map.put("x-dead-letter-exchange",D_EXCHANGE);
        map.put("x-dead-letter-routing-key","YD");
        map.put("x-message-ttl",40000);
        return QueueBuilder.durable(X_QUEUE_B).withArguments(map).build();
    }

    //声明队列
    @Bean("queueD")
    public Queue queueD() {
        return QueueBuilder.durable(DEATH_QUEUE_D).build();
    }



    //绑定
    @Bean
    public Binding bindingQA(@Qualifier("queueA") Queue a,
                             @Qualifier("directExchangex") DirectExchange e){
        return BindingBuilder.bind(a).to(e).with("XA");
    }


    @Bean
    public Binding bindingQB(@Qualifier("queueB") Queue a,
                             @Qualifier("directExchangex") DirectExchange e){
        return BindingBuilder.bind(a).to(e).with("XB");
    }

    @Bean
    public Binding bindingQD(@Qualifier("queueD") Queue a,
                             @Qualifier("directExchangey") DirectExchange e){
        return BindingBuilder.bind(a).to(e).with("YD");
    }

}
