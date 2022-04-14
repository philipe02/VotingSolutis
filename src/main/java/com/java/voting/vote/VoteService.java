package com.java.voting.vote;

import com.java.voting.associate.Associate;
import com.java.voting.associate.AssociateRepository;
import com.java.voting.exception.InvalidVotingStatusException;
import com.java.voting.exception.VoteAlreadyRegisteredException;
import com.java.voting.exception.VotingClosedException;
import com.java.voting.utils.VotingUtils;
import com.java.voting.voting.Voting;
import com.java.voting.voting.VotingRepository;
import com.java.voting.voting.VotingStatus;
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
    VotingRepository votingRepository;
    @Autowired
    AssociateRepository associateRepository;

    public List<VoteDTO> getAllVotes() {
        List<Vote> voteList = repository.findAll();
        return voteList.stream().map(VoteDTO::createVoteDTO).toList();
    }

    public Vote saveVote(VoteViewModel vote) {
        Voting voting = votingRepository.findById(vote.idVoting()).orElseThrow();
        Associate associate = associateRepository.findById(vote.idAssociate()).orElseThrow();

        if(voting.getStatus() != VotingStatus.VOTING)
            throw new InvalidVotingStatusException("Voting is not in progress");

        if(repository.existsByVotingAndAssociate(voting, associate))
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

        return repository.save(voteToSave);
    }

    private Boolean isVoteInTime(LocalDateTime startTime, LocalDateTime endTime){
        LocalDateTime agora =  LocalDateTime.now(clock);
        return LocalDateTime.now(clock).isAfter(startTime) && LocalDateTime.now(clock).isBefore(endTime);
    }
}
