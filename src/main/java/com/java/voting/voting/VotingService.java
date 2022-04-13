package com.java.voting.voting;

import com.java.voting.exception.InvalidVotingStatusException;
import com.java.voting.exception.VotingAlreadyExistsException;
import com.java.voting.topic.Topic;
import com.java.voting.topic.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;

@Service
public class VotingService {

    @Autowired
    Clock clock;
    @Autowired
    VotingRepository repository;
    @Autowired
    TopicRepository topicRepository;

    public VotingDTO getVotingById(Long idVoting){
        return VotingDTO.createVotingDTO(repository.findById(idVoting).orElseThrow());
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
            throw new InvalidVotingStatusException("Voting already " + (voting.getStatus() == VotingStatus.VOTING ? "started" : "happened"));

        LocalDateTime startTime = LocalDateTime.now(clock);
        LocalDateTime endTime = LocalDateTime.now(clock).plusSeconds(durationInSeconds);

        repository.startVoting(voting.getIdVoting(), startTime, endTime);

        return "Voting for "+voting.getTopic().getTitle()+ " has started";
    }
}
