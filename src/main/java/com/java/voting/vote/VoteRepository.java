package com.java.voting.vote;

import com.java.voting.associate.Associate;
import com.java.voting.voting.Voting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    Boolean existsByVotingAndAssociate(Voting voting, Associate associate);
}