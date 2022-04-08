package com.java.voting.vote;

import com.java.voting.voting.Voting;
import com.java.voting.voting.VotingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {

    @Autowired
    VoteRepository repository;
    @Autowired
    VotingRepository votingRepository;

    public List<Vote> getAllVotes() {
        return repository.findAll();
    }

    public Object saveVote(Vote vote) {
        Voting voting = votingRepository.findById(vote.getIdVoting()).orElseThrow();

        if (voting.getStatus().equals("closed"))
            return "Voting is closed";

        if(vote.getOption() != 1 && vote.getOption() != 2)
            return "Invalid option";

        if (vote.getOption() == 1)
            voting.setPositiveVotes(voting.getPositiveVotes() + 1);

        if (vote.getOption() == 2)
            voting.setNegativeVotes(voting.getNegativeVotes() + 1);

        votingRepository.save(voting);

        return repository.save(vote);
    }
}
