package com.java.voting.config.messager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageReceiver {

    public void receive(String msg){
        log.info(msg);
    }
}
