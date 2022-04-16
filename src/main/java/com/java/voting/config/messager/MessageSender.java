package com.java.voting.config.messager;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class MessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String message){
        this.rabbitTemplate.convertAndSend(MessengerConfig.topicExchangeName,"voting.results", message);
    }
}
