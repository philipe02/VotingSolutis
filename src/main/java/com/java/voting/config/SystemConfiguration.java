package com.java.voting.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;
import java.time.Clock;

@Configuration
public class SystemConfiguration {
    @Bean
    public static Clock clock(){
        return Clock.systemDefaultZone();
    }

    @Bean
    public Queue queue(){
        return new ActiveMQQueue("voting");
    }
}
