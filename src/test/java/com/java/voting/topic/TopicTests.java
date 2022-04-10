package com.java.voting.topic;

import com.java.voting.voting.Voting;
import com.java.voting.voting.VotingRepository;
import com.java.voting.voting.VotingStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TopicTests {

    @Autowired
    private TopicService service;
    @MockBean
    private TopicRepository repository;
    @MockBean
    private VotingRepository votingRepository;

    @BeforeAll
    void  setup(){
        Topic topic = Topic.builder().idTopic(1L).title("Teste").build();
        Voting voting = Voting.builder().idVoting(1L).topic(topic).positiveVotes(3).negativeVotes(1).status(VotingStatus.CLOSED).build();

        Mockito.when(repository.findById(topic.getIdTopic())).thenReturn(Optional.of(topic));
        Mockito.when(votingRepository.findByTopic(topic)).thenReturn(Optional.ofNullable(voting));
    }

    @Test
    void shouldReturnTopicWithCorrectValues(){
        Long id = 1L;
        TopicViewModel topicReturned = TopicViewModel.builder().idTopic(id).title("Teste").result("In favour").positiveVotes(3).negativeVotes(1).positiveRatio(75.0).negativeRatio(25.0).build();

        Assertions.assertEquals(topicReturned, service.getTopicById(id));
    }


}
