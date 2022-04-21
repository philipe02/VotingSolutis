package com.java.voting.messenger;

import com.java.voting.config.MessengerConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String message){
        this.rabbitTemplate.convertAndSend(MessengerConfig.TOPIC_EXCHANGE_NAME,"voting.results", message);
    }
}
