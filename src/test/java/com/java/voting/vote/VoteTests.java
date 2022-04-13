package com.java.voting.vote;

import com.java.voting.associate.Associate;
import com.java.voting.associate.AssociateRepository;
import com.java.voting.exception.VoteAlreadyRegisteredException;
import com.java.voting.exception.VotingClosedException;
import com.java.voting.topic.Topic;
import com.java.voting.voting.Voting;
import com.java.voting.voting.VotingRepository;
import com.java.voting.voting.VotingStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class VoteTests {

    @Autowired
    Clock clock;
    @Autowired
    VoteService service;
    @MockBean
    VoteRepository repository;
    @MockBean
    VotingRepository votingRepository;
    @MockBean
    AssociateRepository associateRepository;

    Associate associate1 = Associate.builder().idAssociate(1L).name("Jose").build();
    Associate associate2 = Associate.builder().idAssociate(2L).name("Maria").build();

    Voting votingOpen = Voting.builder()
            .idVoting(1L)
            .topic(new Topic(1L, "Test", null))
            .positiveVotes(0)
            .negativeVotes(0)
            .status(VotingStatus.OPEN)
            .build();
    Voting votingClosed = Voting.builder()
            .idVoting(2L)
            .topic(new Topic(2L, "Test closed", null))
            .positiveVotes(0)
            .negativeVotes(0)
            .status(VotingStatus.CLOSED)
            .build();

    @BeforeEach
    void setup(){
        Mockito.when(votingRepository.findById(votingOpen.getIdVoting())).thenReturn(Optional.of(votingOpen));
        Mockito.when(votingRepository.findById(votingClosed.getIdVoting())).thenReturn(Optional.of(votingClosed));

        Mockito.when(associateRepository.findById(associate1.getIdAssociate())).thenReturn(Optional.of(associate1));
        Mockito.when(associateRepository.findById(associate2.getIdAssociate())).thenReturn(Optional.of(associate2));
    }

    @Test
    void shouldSaveVoteSuccessfully(){
        Associate associate1 = Associate.builder().idAssociate(1L).name("Jose").build();
        Associate associate2 = Associate.builder().idAssociate(2L).name("Maria").build();

        VoteViewModel voteInFavour = VoteViewModel.builder()
                .idAssociate(associate1.getIdAssociate())
                .idVoting(votingOpen.getIdVoting())
                .inFavour(true)
                .build();
        VoteViewModel voteNotInFavour = VoteViewModel.builder()
                .idAssociate(associate2.getIdAssociate())
                .idVoting(votingOpen.getIdVoting())
                .inFavour(false)
                .build();

        Vote voteInFavourToSave = Vote.builder()
                .associate(associate1)
                .voting(votingOpen)
                .votingTime(LocalDateTime.now(clock))
                .inFavour(true)
                .build();
        Vote voteNotInFavourToSave = Vote.builder()
                .associate(associate2)
                .voting(votingOpen)
                .votingTime(LocalDateTime.now(clock))
                .inFavour(true)
                .build();

        Vote voteInFavourReturned = Vote.builder().idVote(1L)
                .associate(associate1)
                .voting(votingOpen)
                .votingTime(LocalDateTime.now(clock))
                .inFavour(true)
                .build();
        Vote voteNotInFavourReturned = Vote.builder()
                .idVote(2L)
                .associate(associate2)
                .voting(votingOpen)
                .votingTime(LocalDateTime.now(clock))
                .inFavour(true)
                .build();

        Mockito.when(repository.existsByVotingAndAssociate(voteInFavourReturned.getVoting(), voteInFavourReturned.getAssociate())).thenReturn(false);
        Mockito.when(repository.existsByVotingAndAssociate(voteNotInFavourReturned.getVoting(), voteNotInFavourReturned.getAssociate())).thenReturn(false);
        Mockito.when(repository.save(voteInFavourToSave)).thenReturn(voteInFavourReturned);
        Mockito.when(repository.save(voteNotInFavourToSave)).thenReturn(voteNotInFavourReturned);

        Assertions.assertEquals(voteInFavourReturned, service.saveVote(voteInFavour));
        Assertions.assertEquals(voteNotInFavourReturned, service.saveVote(voteNotInFavour));
    }

    @Test
    void shouldThrowVotingClosedException(){
        VoteViewModel voteToClosedVoting = VoteViewModel.builder()
                .idAssociate(associate1.getIdAssociate())
                .idVoting(votingClosed.getIdVoting())
                .inFavour(true)
                .build();

        Assertions.assertThrows(VotingClosedException.class, () -> service.saveVote(voteToClosedVoting));
    }

    @Test
    void shouldThrowVotingAlreadyRegisteredException(){
        VoteViewModel vote = VoteViewModel.builder()
                .idAssociate(associate1.getIdAssociate())
                .idVoting(votingOpen.getIdVoting())
                .inFavour(true)
                .build();

        Mockito.when(repository.existsByVotingAndAssociate(votingOpen, associate1)).thenReturn(true);

        Assertions.assertThrows(VoteAlreadyRegisteredException.class, ()->service.saveVote(vote));
    }
}
