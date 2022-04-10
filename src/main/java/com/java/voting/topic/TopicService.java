package com.java.voting.topic;

import com.java.voting.voting.Voting;
import com.java.voting.voting.VotingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

    @Autowired
    TopicRepository repository;
    @Autowired
    VotingRepository votingRepository;

    public TopicViewModel getTopicById(Long idTopic){
        Topic topic = repository.findById(idTopic).orElseThrow();
        Voting voting = votingRepository.findByTopic(topic).orElse(new Voting());

        return TopicViewModel.createTopicViewModel(topic, voting);
    }

    public Topic saveTopic(Topic topicToSave){
        return repository.save(topicToSave);
    }

}
