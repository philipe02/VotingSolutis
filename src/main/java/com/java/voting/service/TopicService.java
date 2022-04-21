package com.java.voting.service;

import com.java.voting.model.dto.TopicDTO;
import com.java.voting.model.entity.Topic;
import com.java.voting.model.form.TopicForm;
import com.java.voting.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

    @Autowired
    TopicRepository repository;

    public TopicDTO getTopicById(Long idTopic){
        Topic topic = repository.findById(idTopic).orElseThrow();

        return TopicDTO.createTopicDTO(topic);
    }

    public TopicDTO saveTopic(TopicForm topicDTO){
        Topic topicToSave = Topic.builder().title(topicDTO.title()).description(topicDTO.description()).build();

        return TopicDTO.createTopicDTO(repository.save(topicToSave));
    }
}
