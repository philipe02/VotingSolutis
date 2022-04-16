package com.java.voting.vote;

import com.java.voting.associate.Associate;
import com.java.voting.associate.AssociateRepository;
import com.java.voting.exception.InvalidCpfException;
import com.java.voting.exception.InvalidVotingStatusException;
import com.java.voting.exception.VoteAlreadyRegisteredException;
import com.java.voting.exception.VotingClosedException;
import com.java.voting.external.cpfValidation.CpfValidationService;
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
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
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
    CpfValidationService cpfValidationService;
    @MockBean
    VotingRepository votingRepository;
    @MockBean
    AssociateRepository associateRepository;

    Associate associate1 = Associate.builder().idAssociate(1L).name("Jose").cpf("1").build();
    Associate associate2 = Associate.builder().idAssociate(2L).name("Maria").cpf("1").build();
    Associate associateCpfInvalid = Associate.builder().idAssociate(3L).name("Fulano").cpf("2").build();

    @TestConfiguration
    public static class Config {
        @Bean
        public Clock clock(){
            return Clock.fixed(Instant.parse("2021-09-10T12:00:00Z"), ZoneOffset.UTC);
        }
    }

    Voting votingOpen = Voting.builder()
            .idVoting(1L)
            .topic(new Topic(1L, "Test", null, null))
            .positiveVotes(0)
            .negativeVotes(0)
            .status(VotingStatus.OPEN)
            .build();
    Voting votingClosed = Voting.builder()
            .idVoting(2L)
            .topic(new Topic(2L, "Test closed", null, null))
            .positiveVotes(0)
            .negativeVotes(0)
            .status(VotingStatus.CLOSED)
            .build();
    Voting votingNotCollected = Voting.builder()
            .idVoting(3L)
            .topic(new Topic(3L, "Test Not Collect", null, null))
            .positiveVotes(0)
            .negativeVotes(0)
            .status(VotingStatus.VOTING)
            .build();
    Voting votingInProgress = Voting.builder()
            .idVoting(4L)
            .topic(new Topic(4L, "Test In Progress", null, null))
            .positiveVotes(0)
            .negativeVotes(0)
            .status(VotingStatus.VOTING)
            .build();

    @BeforeEach
    void setup(){
        votingClosed.setStartTime(LocalDateTime.now(clock).minusMinutes(2));
        votingClosed.setEndTime(LocalDateTime.now(clock).minusMinutes(1));

        votingNotCollected.setStartTime(LocalDateTime.now(clock).minusMinutes(2));
        votingNotCollected.setEndTime(LocalDateTime.now(clock).minusMinutes(1));

        votingInProgress.setStartTime(LocalDateTime.now(clock).minusMinutes(1));
        votingInProgress.setEndTime(LocalDateTime.now(clock).plusMinutes(1));

        Mockito.when(cpfValidationService.validateCpf("1")).thenReturn(true);
        Mockito.when(cpfValidationService.validateCpf("2")).thenThrow(InvalidCpfException.class);

        Mockito.when(votingRepository.findById(votingOpen.getIdVoting())).thenReturn(Optional.of(votingOpen));
        Mockito.when(votingRepository.findById(votingClosed.getIdVoting())).thenReturn(Optional.of(votingClosed));
        Mockito.when(votingRepository.findById(votingNotCollected.getIdVoting())).thenReturn(Optional.of(votingNotCollected));
        Mockito.when(votingRepository.findById(votingInProgress.getIdVoting())).thenReturn(Optional.of(votingInProgress));

        Mockito.when(associateRepository.findById(associate1.getIdAssociate())).thenReturn(Optional.of(associate1));
        Mockito.when(associateRepository.findById(associate2.getIdAssociate())).thenReturn(Optional.of(associate2));
    }

    @Test
    void shouldSaveVoteSuccessfully(){
        Associate associate1 = Associate.builder().idAssociate(1L).name("Jose").build();
        Associate associate2 = Associate.builder().idAssociate(2L).name("Maria").build();

        VoteViewModel voteInFavour = VoteViewModel.builder()
                .idAssociate(associate1.getIdAssociate())
                .idVoting(votingInProgress.getIdVoting())
                .inFavour(true)
                .build();
        VoteViewModel voteNotInFavour = VoteViewModel.builder()
                .idAssociate(associate2.getIdAssociate())
                .idVoting(votingInProgress.getIdVoting())
                .inFavour(false)
                .build();
        Vote voteInFavourToSave = Vote.builder()
                .associate(associate1)
                .voting(votingInProgress)
                .votingTime(LocalDateTime.now(clock))
                .inFavour(true)
                .build();
        Vote voteNotInFavourToSave = Vote.builder()
                .associate(associate2)
                .voting(votingInProgress)
                .votingTime(LocalDateTime.now(clock))
                .inFavour(false)
                .build();

        Vote voteInFavourReturned = Vote.builder().idVote(1L)
                .associate(associate1)
                .voting(votingInProgress)
                .votingTime(LocalDateTime.now(clock))
                .inFavour(true)
                .build();
        Vote voteNotInFavourReturned = Vote.builder()
                .idVote(2L)
                .associate(associate2)
                .voting(votingInProgress)
                .votingTime(LocalDateTime.now(clock))
                .inFavour(false)
                .build();

        Mockito.when(repository.existsByVotingAndAssociate(voteInFavourReturned.getVoting(), voteInFavourReturned.getAssociate())).thenReturn(false);
        Mockito.when(repository.existsByVotingAndAssociate(voteNotInFavourReturned.getVoting(), voteNotInFavourReturned.getAssociate())).thenReturn(false);
        Mockito.when(repository.save(voteInFavourToSave)).thenReturn(voteInFavourReturned);
        Mockito.when(repository.save(voteNotInFavourToSave)).thenReturn(voteNotInFavourReturned);

        Assertions.assertEquals(voteInFavourReturned, service.saveVote(voteInFavour));
        Assertions.assertEquals(voteNotInFavourReturned, service.saveVote(voteNotInFavour));
    }

    @Test
    void shouldReturnVoteList(){
        Vote voteInFavourReturned = Vote.builder().idVote(1L)
                .associate(associate1)
                .voting(votingInProgress)
                .votingTime(LocalDateTime.now(clock))
                .inFavour(true)
                .build();

        Mockito.when(repository.findAll()).thenReturn(List.of(voteInFavourReturned));

        Assertions.assertEquals(VoteDTO.createVoteDTO(voteInFavourReturned), service.getAllVotes().get(0));
    }

    @Test
    void shouldThrowInvalidVotingStatus(){
        VoteViewModel voteToNotInProgressVoting = VoteViewModel.builder()
                .idAssociate(associate1.getIdAssociate())
                .idVoting(votingOpen.getIdVoting())
                .inFavour(true)
                .build();

        Assertions.assertThrows(InvalidVotingStatusException.class, () -> service.saveVote(voteToNotInProgressVoting));
    }

    @Test
    void shouldThrowVotingClosedException(){
        VoteViewModel voteToClosedVoting = VoteViewModel.builder()
                .idAssociate(associate1.getIdAssociate())
                .idVoting(votingNotCollected.getIdVoting())
                .inFavour(true)
                .build();

        Assertions.assertThrows(VotingClosedException.class, () -> service.saveVote(voteToClosedVoting));
    }

    @Test
    void shouldThrowVotingAlreadyRegisteredException(){
        VoteViewModel vote = VoteViewModel.builder()
                .idAssociate(associate1.getIdAssociate())
                .idVoting(votingInProgress.getIdVoting())
                .inFavour(true)
                .build();

        Mockito.when(repository.existsByVotingAndAssociate(votingInProgress, associate1)).thenReturn(true);

        Assertions.assertThrows(VoteAlreadyRegisteredException.class, ()->service.saveVote(vote));
    }
}
