package com.java.voting.vote;

import com.java.voting.associate.Associate;
import com.java.voting.associate.AssociateRepository;
import com.java.voting.exception.VoteAlreadyRegisteredException;
import com.java.voting.exception.VotingAlreadyExistsException;
import com.java.voting.exception.VotingClosedException;
import com.java.voting.topic.Topic;
import com.java.voting.voting.Voting;
import com.java.voting.voting.VotingRepository;
import com.java.voting.voting.VotingStatus;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.time.*;
import java.util.Optional;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class VoteTests {

    @Autowired
    VoteService service;
    @Autowired
    Clock clock;
    @MockBean
    VoteRepository repository;
    @MockBean
    VotingRepository votingRepository;
    @MockBean
    AssociateRepository associateRepository;

    @BeforeEach
    void setup(){
        Associate associate1 = Associate.builder().idAssociate(1L).name("Jose").build();
        Associate associate2 = Associate.builder().idAssociate(2L).name("Maria").build();
        Voting votingOpen = Voting.builder().idVoting(1L).topic(new Topic(1L, "Test", null)).positiveVotes(0).negativeVotes(0).status(VotingStatus.OPEN).build();
        Voting votingClosed = Voting.builder().idVoting(2L).topic(new Topic(2L, "Test closed", null)).positiveVotes(0).negativeVotes(0).status(VotingStatus.CLOSED).build();

        Mockito.when(votingRepository.findById(votingOpen.getIdVoting())).thenReturn(Optional.of(votingOpen));
        Mockito.when(votingRepository.findById(votingClosed.getIdVoting())).thenReturn(Optional.of(votingClosed));

        Mockito.when(associateRepository.findById(associate1.getIdAssociate())).thenReturn(Optional.of(associate1));
        Mockito.when(associateRepository.findById(associate2.getIdAssociate())).thenReturn(Optional.of(associate2));
    }

    @Test
    void shouldSaveVoteSuccessfully(){
        Vote voteInFavour = Vote.builder().idAssociate(1L).idVoting(1L).inFavour(true).build();
        Vote voteNotInFavour = Vote.builder().idAssociate(2L).idVoting(1L).inFavour(false).build();
        Vote voteInFavourReturned = Vote.builder().idVote(1L).idAssociate(1L).idVoting(1L).votingTime(LocalDateTime.now(clock)).inFavour(true).build();
        Vote voteNotInFavourReturned = Vote.builder().idVote(2L).idAssociate(2L).idVoting(1L).votingTime(LocalDateTime.now(clock)).inFavour(true).build();

        voteInFavour.setVotingTime(LocalDateTime.now());
        voteNotInFavour.setVotingTime(LocalDateTime.now());

        Mockito.when(repository.existsByIdVotingAndIdAssociate(voteInFavour.getIdVoting(), voteInFavour.getIdAssociate())).thenReturn(false);
        Mockito.when(repository.existsByIdVotingAndIdAssociate(voteNotInFavour.getIdVoting(), voteNotInFavour.getIdAssociate())).thenReturn(false);
        Mockito.when(repository.save(voteInFavour)).thenReturn(voteInFavourReturned);
        Mockito.when(repository.save(voteNotInFavour)).thenReturn(voteNotInFavourReturned);

        Assertions.assertEquals(voteInFavourReturned, service.saveVote(voteInFavour));
        Assertions.assertEquals(voteNotInFavourReturned, service.saveVote(voteNotInFavour));
    }

    @Test
    void shouldThrowVotingClosedException(){
        Vote voteToClosedVoting = Vote.builder().idAssociate(1L).idVoting(2L).votingTime(LocalDateTime.now(clock)).inFavour(true).build();

        Assertions.assertThrows(VotingClosedException.class, () -> service.saveVote(voteToClosedVoting));
    }

    @Test
    void shouldThrowVotingAlreadyRegisteredException(){
        Vote vote = Vote.builder().idAssociate(1L).idVoting(1L).inFavour(true).build();

        Mockito.when(repository.existsByIdVotingAndIdAssociate(vote.getIdVoting(), vote.getIdAssociate())).thenReturn(true);

        Assertions.assertThrows(VoteAlreadyRegisteredException.class, ()->service.saveVote(vote));
    }
}
