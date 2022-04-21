package com.java.voting.voting;

import com.java.voting.exception.InvalidVotingStatusException;
import com.java.voting.exception.VotingAlreadyExistsException;
import com.java.voting.model.dto.VotingDTO;
import com.java.voting.model.dto.VotingResultsDTO;
import com.java.voting.model.entity.Voting;
import com.java.voting.model.entity.VotingResults;
import com.java.voting.model.entity.Topic;
import com.java.voting.enums.VotingStatus;
import com.java.voting.repository.VotingRepository;
import com.java.voting.repository.VotingResultsRepository;
import com.java.voting.service.VotingService;
import com.java.voting.repository.TopicRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
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
    @MockBean
    VotingRepository repository;
    @MockBean
    TopicRepository topicRepository;
    @MockBean
    VotingResultsRepository votingResultsRepository;

    @TestConfiguration
    public static class Config {
        @Bean
        public Clock clock(){
            return Clock.fixed(Instant.parse("2021-09-10T12:00:00Z"), ZoneOffset.UTC);
        }
    }

    Topic topic = Topic.builder().idTopic(1L).title("Test").build();

    Voting votingOpen = Voting.builder().idVoting(1L).topic(topic).positiveVotes(0).negativeVotes(0).status(VotingStatus.OPEN).build();
    Voting votingClosed = Voting.builder().idVoting(1L).topic(topic).positiveVotes(3).negativeVotes(1).status(VotingStatus.CLOSED).build();
    Voting votingInProgress = Voting.builder().idVoting(1L).topic(topic).positiveVotes(3).negativeVotes(1).status(VotingStatus.VOTING).build();

    @BeforeEach
    void setup(){
        Mockito.when(topicRepository.findById(topic.getIdTopic())).thenReturn(Optional.of(topic));
    }

    @Test
    void shouldCreateVotingSuccessfully(){
        Voting votingToSave = new Voting(topic);

        Mockito.when(repository.save(votingToSave)).thenReturn(votingOpen);

        Assertions.assertEquals(VotingDTO.createVotingDTO(votingOpen), service.createVoting(topic.getIdTopic()));
    }

    @Test
    void shouldThrowVotingAlreadyExists(){
        Mockito.when(repository.existsByTopic(topic)).thenReturn(true);

        Assertions.assertThrows(VotingAlreadyExistsException.class, () -> service.createVoting(1L));
    }

    @Test
    void shouldStartVotingSuccessfully(){
        Mockito.when(repository.findById(votingOpen.getIdVoting())).thenReturn(Optional.of(votingOpen));

        Assertions.assertEquals(service.startVoting(votingOpen.getIdVoting(), 1), "Voting for " + topic.getTitle() + " has started");
    }

    @Test
    void shouldThrowInvalidVotingStatusException(){
        Mockito.when(repository.findById(votingClosed.getIdVoting())).thenReturn(Optional.of(votingClosed));

        Assertions.assertThrows(InvalidVotingStatusException.class, () -> service.startVoting(1L, 1));
    }

    @Test
    void shouldShowVotingResults(){
        VotingResults votingResults = VotingResults.builder()
                .idVotingResult(1L)
                .voting(votingClosed)
                .result("In favour")
                .positiveRatio(75.0)
                .negativeRatio(25.0)
                .build();

        Mockito.when(repository.findById(votingClosed.getIdVoting())).thenReturn(Optional.of(votingClosed));
        Mockito.when(votingResultsRepository.findByVoting(votingClosed)).thenReturn(Optional.of(votingResults));

        Assertions.assertEquals(VotingResultsDTO.createVotingResultsDTO(votingResults), service.showResultsOfVoting(votingClosed.getIdVoting()));
    }

    @Test
    void shouldThrowInvalidVotingStatusWhenGetResults(){
        Mockito.when(repository.findById(votingInProgress.getIdVoting())).thenReturn(Optional.of(votingInProgress));

        Assertions.assertThrows(InvalidVotingStatusException.class, () -> service.showResultsOfVoting(1L));
    }
}
