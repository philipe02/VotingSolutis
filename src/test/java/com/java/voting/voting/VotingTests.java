package com.java.voting.voting;

import com.java.voting.exception.InvalidVotingStatusException;
import com.java.voting.exception.VotingAlreadyExistsException;
import com.java.voting.topic.Topic;
import com.java.voting.topic.TopicRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Optional;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class VotingTests {

    @Autowired
    VotingService service;
    @Autowired
    Clock clock;
    @MockBean
    VotingRepository repository;
    @MockBean
    TopicRepository topicRepository;

    @BeforeEach
    void setup(){
        Topic topic = Topic.builder().idTopic(1L).title("Test").build();

        Mockito.when(topicRepository.findById(topic.getIdTopic())).thenReturn(Optional.of(topic));

    }

    @Test
    void shouldCreateVotingSuccessfully(){
        Topic topic = Topic.builder().idTopic(1L).title("Test").build();
        Voting votingToSave = new Voting(topic);
        Voting votingReturned = Voting.builder().idVoting(1L).topic(topic).positiveVotes(0).negativeVotes(0).status(VotingStatus.OPEN).build();

        Mockito.when(repository.save(votingToSave)).thenReturn(votingReturned);

        Assertions.assertEquals(votingReturned, service.createVoting(topic.getIdTopic()));
    }

    @Test
    void shouldThrowVotingAlreadyExists(){
        Topic topic = Topic.builder().idTopic(1L).title("Test").build();
        Voting votingToSave = new Voting(topic);

        Mockito.when(repository.existsByTopic(topic)).thenReturn(true);

        Assertions.assertThrows(VotingAlreadyExistsException.class, () -> service.createVoting(topic.getIdTopic()));
    }

    @Test
    void shouldStartVotingSuccessfully(){
        Topic topic = Topic.builder().idTopic(1L).title("Test").build();
        Voting voting = Voting.builder().idVoting(1L).topic(topic).positiveVotes(0).negativeVotes(0).status(VotingStatus.OPEN).build();

        Mockito.when(repository.findById(voting.getIdVoting())).thenReturn(Optional.of(voting));

        Assertions.assertEquals(service.startVoting(voting.getIdVoting(), 1), "Voting for " + topic.getTitle() + " has started");
    }

    @Test
    void shouldThrowInvalidVotingStatusException(){
        Topic topic = Topic.builder().idTopic(1L).title("Test").build();
        Voting voting = Voting.builder().idVoting(1L).topic(topic).positiveVotes(0).negativeVotes(0).status(VotingStatus.CLOSED).build();

        Mockito.when(repository.findById(voting.getIdVoting())).thenReturn(Optional.of(voting));

        Assertions.assertThrows(InvalidVotingStatusException.class, () -> service.startVoting(voting.getIdVoting(), 1));
    }
}
