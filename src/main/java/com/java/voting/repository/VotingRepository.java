package com.java.voting.repository;

import com.java.voting.model.entity.Voting;
import com.java.voting.model.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VotingRepository extends JpaRepository<Voting, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE voting SET status = 2 WHERE id_voting=?1", nativeQuery = true)
    void closeVoting(Long idVoting);

    @Modifying
    @Transactional
    @Query(value = "UPDATE voting SET status = 1, start_time=?2, end_time=?3 WHERE id_voting=?1", nativeQuery = true)
    void startVoting(Long idVoting, LocalDateTime startTime, LocalDateTime endTime);

    boolean existsByTopic(Topic topic);

    Optional<Voting> findByTopic(Topic topic);

    @Query(value = "SELECT * FROM voting WHERE status=1 AND end_time < CURRENT_TIMESTAMP", nativeQuery = true)
    List<Voting> findAllVotingEndedAndNotClosed();

}