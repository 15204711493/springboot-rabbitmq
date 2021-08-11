package com.atguigu.rabbitmq.springbootrabbitmq.consumer;

import com.atguigu.rabbitmq.springbootrabbitmq.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class Consumer {

    @RabbitListener(queues = ConfirmConfig.q_queue)
    public void re(Message message){
        String m = new String(message.getBody());
        log.info("当前时间:{},收到队列消息:{}",new Date().toString(),m);
    }
}
