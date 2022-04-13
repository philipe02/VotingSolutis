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

    @BeforeAll
    void  setup(){
        Topic topic = Topic.builder().idTopic(1L).title("Teste").build();

        Mockito.when(repository.findById(topic.getIdTopic())).thenReturn(Optional.of(topic));
    }

    @Test
    void shouldReturnTopicWithCorrectValues(){
        TopicDTO topicReturned = TopicDTO.builder().idTopic(1L).title("Teste").build();

        Assertions.assertEquals(topicReturned, service.getTopicById(topicReturned.idTopic()));
    }


}
