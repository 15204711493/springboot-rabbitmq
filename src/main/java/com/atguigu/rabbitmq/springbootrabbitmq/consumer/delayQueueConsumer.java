package com.atguigu.rabbitmq.springbootrabbitmq.consumer;

import com.atguigu.rabbitmq.springbootrabbitmq.config.DelayedQueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

//插件
@Slf4j
@Component
public class delayQueueConsumer {


    @RabbitListener(queues = DelayedQueueConfig.delayed_queue_name)
    public void re(Message message){
         String msg = new String(message.getBody());
        log.info("当前时间:{},收到延迟队列消息:{}",new Date().toString(),msg);
    }
}
