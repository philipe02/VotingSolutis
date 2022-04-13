package com.java.voting.voting;

import com.java.voting.topic.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public interface VotingRepository extends JpaRepository<Voting, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE voting SET status = 2 WHERE id_voting=?1", nativeQuery = true)
    void closeVoting(Long idVoting, LocalDateTime endTime);

    @Modifying
    @Transactional
    @Query(value = "UPDATE voting SET status = 1, start_time=?2, end_time=?3 WHERE id_voting=?1", nativeQuery = true)
    void startVoting(Long idVoting, LocalDateTime startTime, LocalDateTime endTime);

    boolean existsByTopic(Topic topic);

    Optional<Voting> findByTopic(Topic topic);

}