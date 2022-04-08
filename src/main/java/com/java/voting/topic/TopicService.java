package com.java.voting.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

    @Autowired
    TopicRepository repository;

    public Topic getTopicById(Long id){
        return repository.findById(id).orElseThrow();
    }

    public Topic saveTopic(Topic topicToSave){
        return repository.save(topicToSave);
    }
}
