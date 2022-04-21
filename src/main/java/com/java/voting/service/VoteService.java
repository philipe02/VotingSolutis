package com.java.voting.service;

import com.java.voting.model.entity.Associate;
import com.java.voting.repository.AssociateRepository;
import com.java.voting.enums.VotingStatus;
import com.java.voting.exception.InvalidCpfException;
import com.java.voting.exception.InvalidVotingStatusException;
import com.java.voting.exception.VoteAlreadyRegisteredException;
import com.java.voting.exception.VotingClosedException;
import com.java.voting.external.cpf_validation.CpfValidationService;
import com.java.voting.model.dto.VoteDTO;
import com.java.voting.model.entity.Vote;
import com.java.voting.model.form.VoteForm;
import com.java.voting.model.entity.Voting;
import com.java.voting.repository.VoteRepository;
import com.java.voting.repository.VotingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VoteService {

    @Autowired
    Clock clock;
    @Autowired
    VoteRepository repository;
    @Autowired
    CpfValidationService cpfValidationService;
    @Autowired
    VotingRepository votingRepository;
    @Autowired
    AssociateRepository associateRepository;

    public List<VoteDTO> getAllVotes() {
        List<Vote> voteList = repository.findAll();
        return voteList.stream().map(VoteDTO::createVoteDTO).toList();
    }

    public VoteDTO saveVote(VoteForm vote) {
        Voting voting = votingRepository.findById(vote.idVoting()).orElseThrow();
        Associate associate = associateRepository.findById(vote.idAssociate()).orElseThrow();

        if(voting.getStatus() != VotingStatus.VOTING)
            throw new InvalidVotingStatusException("Voting is not in progress");

        if(!cpfValidationService.validateCpf(associate.getCpf()))
            throw new InvalidCpfException("Cpf from associate is invalid");

        if(Boolean.TRUE.equals(repository.existsByVotingAndAssociate(voting, associate)))
           throw new VoteAlreadyRegisteredException("Vote for this topic already registered");

        if (!this.isVoteInTime(voting.getStartTime(), voting.getEndTime()))
            throw new VotingClosedException("Voting is already closed");

        Vote voteToSave = Vote.builder()
                .associate(associate)
                .voting(voting)
                .inFavour(vote.inFavour())
                .build();

        if (voteToSave.getInFavour())
            voting.setPositiveVotes(voting.getPositiveVotes() + 1);
        else
            voting.setNegativeVotes(voting.getNegativeVotes() + 1);

        voteToSave.setVotingTime(LocalDateTime.now(clock));

        votingRepository.save(voting);

        return VoteDTO.createVoteDTO(repository.save(voteToSave));
    }

    private Boolean isVoteInTime(LocalDateTime startTime, LocalDateTime endTime){
        return LocalDateTime.now(clock).isAfter(startTime) && LocalDateTime.now(clock).isBefore(endTime);
    }
}
