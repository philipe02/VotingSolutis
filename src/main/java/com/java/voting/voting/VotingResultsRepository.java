package com.java.voting.voting;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VotingResultsRepository extends JpaRepository<VotingResults, Long> {

    Optional<VotingResults> findByVoting(Voting voting);
}