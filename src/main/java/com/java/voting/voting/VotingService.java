package com.java.voting.voting;

import com.java.voting.exception.VotingAlreadyExists;
import com.java.voting.exception.VotingClosed;
import com.java.voting.topic.Topic;
import com.java.voting.topic.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VotingService {

    @Autowired
    VotingRepository repository;
    @Autowired
    TopicRepository topicRepository;

    public Voting getVotingById(Long idVoting){
        return repository.findById(idVoting).orElseThrow();
    }

    public Voting createVoting(Long idTopic){
        Topic topic = topicRepository.findById(idTopic).orElseThrow();

        if (repository.existsByTopic(topic))
            throw new VotingAlreadyExists("Voting for this topic has already been created");

        return repository.save(new Voting(topic));
    }

    public Object startVoting(Long idVoting, Integer durationInSeconds){
        Voting voting = repository.findById(idVoting).orElseThrow();

        if (voting.getStatus() != VotingStatus.OPEN)
            throw new VotingClosed("Voting already " + (voting.getStatus() == VotingStatus.VOTING ? "iniciada" : "ocorreu"));

        repository.startVoting(voting.getIdVoting(), LocalDateTime.now());
        votingTimer(voting, durationInSeconds);

        return "Voting for "+voting.getTopic().getTitle()+ " has started";
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
                    repository.closeVoting(voting.getIdVoting(), LocalDateTime.now());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Votação encerrada");
            }).start();

    }
}
