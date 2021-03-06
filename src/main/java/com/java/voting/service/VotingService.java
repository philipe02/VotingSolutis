package com.java.voting.service;

import com.java.voting.exception.InvalidVotingStatusException;
import com.java.voting.exception.VotingAlreadyExistsException;
import com.java.voting.model.dto.VotingDTO;
import com.java.voting.model.dto.VotingResultsDTO;
import com.java.voting.model.entity.Voting;
import com.java.voting.model.entity.VotingResults;
import com.java.voting.model.entity.Topic;
import com.java.voting.enums.VotingStatus;
import com.java.voting.repository.TopicRepository;
import com.java.voting.repository.VotingRepository;
import com.java.voting.repository.VotingResultsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;

@Service
@Slf4j
public class VotingService {

    @Autowired
    Clock clock;
    @Autowired
    VotingRepository repository;
    @Autowired
    TopicRepository topicRepository;
    @Autowired
    VotingResultsRepository votingResultsRepository;

    public VotingDTO getVotingById(Long idVoting){
        return repository.findById(idVoting)
                .map(VotingDTO::createVotingDTO)
                .orElseThrow();
    }

    public VotingDTO createVoting(Long idTopic){
        Topic topic = topicRepository.findById(idTopic).orElseThrow();

        if (repository.existsByTopic(topic))
            throw new VotingAlreadyExistsException("Voting for this topic has already been created");

        return VotingDTO.createVotingDTO(repository.save(new Voting(topic)));
    }

    public String startVoting(Long idVoting, Integer durationInSeconds){
        Voting voting = repository.findById(idVoting).orElseThrow();

        if (voting.getStatus() != VotingStatus.OPEN)
            throw new InvalidVotingStatusException("Voting already " + (voting.getStatus() == VotingStatus.VOTING ? "started" : "happened"));

        repository.startVoting(voting.getIdVoting(), LocalDateTime.now(clock), LocalDateTime.now(clock).plusSeconds(durationInSeconds));

        log.info("Voting for "+voting.getTopic().getTitle()+ " has started");

        return "Voting for "+voting.getTopic().getTitle()+ " has started";
    }

    public VotingResultsDTO showResultsOfVoting(Long idVoting){
        Voting voting = repository.findById(idVoting).orElseThrow();

        if(voting.getStatus() != VotingStatus.CLOSED){
            throw new InvalidVotingStatusException("Results not collected yet");
        }

        VotingResults votingResults = votingResultsRepository.findByVoting(voting).orElseThrow();

        return VotingResultsDTO.createVotingResultsDTO(votingResults);
    }
}
