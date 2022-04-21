package com.java.voting.repository;

import com.java.voting.model.entity.Associate;
import com.java.voting.model.entity.Vote;
import com.java.voting.model.entity.Voting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    Boolean existsByVotingAndAssociate(Voting voting, Associate associate);
}