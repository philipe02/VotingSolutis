package com.java.voting.topic;

import com.java.voting.voting.VotingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

    @Autowired
    TopicRepository repository;
    @Autowired
    VotingRepository votingRepository;

    public TopicDTO getTopicById(Long idTopic){
        Topic topic = repository.findById(idTopic).orElseThrow();

        return TopicDTO.createTopicDTO(topic);
    }

    public Topic saveTopic(TopicDTO topicDTO){
        Topic topicToSave = Topic.builder().title(topicDTO.title()).description(topicDTO.description()).build();
        return repository.save(topicToSave);
    }

}
