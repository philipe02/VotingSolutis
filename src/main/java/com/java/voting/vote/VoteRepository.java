package com.java.voting.vote;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    Boolean existsByIdVotingAndIdAssociate(Long idVoting, Long idAssociate);
}