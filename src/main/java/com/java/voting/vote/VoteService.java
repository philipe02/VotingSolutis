package com.java.voting.vote;

import com.java.voting.associate.Associate;
import com.java.voting.associate.AssociateRepository;
import com.java.voting.exception.VoteAlreadyRegisteredException;
import com.java.voting.exception.VotingClosedException;
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
    VoteRepository repository;
    @Autowired
    Clock clock;
    @Autowired
    VotingRepository votingRepository;
    @Autowired
    AssociateRepository associateRepository;

    public List<Vote> getAllVotes() {
        return repository.findAll();
    }

    public Vote saveVote(Vote vote) {
        Voting voting = votingRepository.findById(vote.getIdVoting()).orElseThrow();
        Associate associate = associateRepository.findById(vote.getIdAssociate()).orElseThrow();

        if(repository.existsByIdVotingAndIdAssociate(voting.getIdVoting(), associate.getIdAssociate()))
           throw new VoteAlreadyRegisteredException("Vote for this topic already registered");

        if (voting.getStatus().equals(VotingStatus.CLOSED))
            throw new VotingClosedException("Voting is already closed");

        if (vote.getInFavour())
            voting.setPositiveVotes(voting.getPositiveVotes() + 1);
        else
            voting.setNegativeVotes(voting.getNegativeVotes() + 1);

        vote.setVotingTime(LocalDateTime.now(clock));

        votingRepository.save(voting);

        return repository.save(vote);
    }
}
