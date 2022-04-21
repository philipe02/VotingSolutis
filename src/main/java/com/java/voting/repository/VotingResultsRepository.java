package com.java.voting.repository;

import com.java.voting.model.entity.Voting;
import com.java.voting.model.entity.VotingResults;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VotingResultsRepository extends JpaRepository<VotingResults, Long> {

    Optional<VotingResults> findByVoting(Voting voting);
}