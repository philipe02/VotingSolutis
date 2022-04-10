package com.java.voting.voting;

import com.java.voting.exception.InvalidVotingStatusException;
import com.java.voting.exception.VotingAlreadyExistsException;
import com.java.voting.topic.Topic;
import com.java.voting.topic.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.logging.Logger;

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
            throw new VotingAlreadyExistsException("Voting for this topic has already been created");

        return repository.save(new Voting(topic));
    }

    public String startVoting(Long idVoting, Integer durationInSeconds){
        Voting voting = repository.findById(idVoting).orElseThrow();

        if (voting.getStatus() != VotingStatus.OPEN)
            throw new InvalidVotingStatusException("Voting already " + (voting.getStatus() == VotingStatus.VOTING ? "iniciada" : "ocorreu"));

        repository.startVoting(voting.getIdVoting(), LocalDateTime.now());
        votingTimer(voting, durationInSeconds);

        return "Voting for "+voting.getTopic().getTitle()+ " has started";
    }

    public void votingTimer(Voting voting, Integer seconds){
        new Thread(() -> {
                try{
                    Thread.sleep((long) seconds * 1000);
                    repository.closeVoting(voting.getIdVoting(), LocalDateTime.now());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();

    }
}
