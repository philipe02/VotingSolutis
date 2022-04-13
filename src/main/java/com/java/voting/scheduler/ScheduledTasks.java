package com.java.voting.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class ScheduledTasks {
    Logger log = Logger.getLogger(ScheduledTasks.class.toString());

    @Scheduled(fixedRate = 60 * 1000)
    public void collectVotingResults(){
        //Procurar todas as votações que encerraram com status aberto, salvar VotingResults e alterar status
    }
}
