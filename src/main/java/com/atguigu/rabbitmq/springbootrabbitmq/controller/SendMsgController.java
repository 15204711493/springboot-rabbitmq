package com.atguigu.rabbitmq.springbootrabbitmq.controller;


import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@RestController
@RequestMapping("ttl")
@Api
public class SendMsgController {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sedMsg/{message}")
    public void msg(@PathVariable String message){
        log.info("当前时间:{},发送一条信息给两个队列:{}",new Date().toString(),message);
        rabbitTemplate.convertAndSend("X","XA","消息10S"+message);
        rabbitTemplate.convertAndSend("X","XB","消息40S"+message);
    }
}
