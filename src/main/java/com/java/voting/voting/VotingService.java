package com.java.voting.voting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VotingService {

    @Autowired
    VotingRepository repository;

    public Voting getVotingById(Long idVoting){
        return repository.findById(idVoting).orElseThrow();
    }

    public Voting startVoting(Long idTopic, Integer durationInSeconds){
        Voting voting = repository.save(new Voting(idTopic));
        votingTimer(voting, durationInSeconds);
        return voting;
    }

    public void votingTimer(Voting voting, Integer seconds){
        if (seconds == null) {
            seconds = 60;
        }
        //Necessário para utilizar a variável na lambda function
        Integer finalSeconds = seconds;

        new Thread(() -> {
                System.out.println("Iniciando votação terminando em "+ finalSeconds + " segundos");
                try{
                    Thread.sleep(finalSeconds * 1000);
                    voting.setStatus("closed");
                    voting.setEndTime(LocalDateTime.now());
                    repository.save(voting);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

    }
}
