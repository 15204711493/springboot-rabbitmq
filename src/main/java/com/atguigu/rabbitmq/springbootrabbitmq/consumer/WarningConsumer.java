package com.atguigu.rabbitmq.springbootrabbitmq.consumer;

import com.atguigu.rabbitmq.springbootrabbitmq.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;
@Slf4j
@Component
public class WarningConsumer {

    @RabbitListener(queues = ConfirmConfig.warning_queue)
    public void re(Message message){
        String m = new String(message.getBody());
        log.info("报警{}",m);
    }
}
