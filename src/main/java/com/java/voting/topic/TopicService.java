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
        TopicViewModel viewModel = TopicViewModel.createTopicViewModel(topic, voting);

        if (viewModel.getPositiveVotes() > viewModel.getNegativeVotes())
            viewModel.setResult("In favour");
        else if (viewModel.getPositiveVotes().equals(viewModel.getNegativeVotes()))
            viewModel.setResult("Draw");
        else
            viewModel.setResult("Against");

        return viewModel;
    }

    public Topic saveTopic(Topic topicToSave){
        return repository.save(topicToSave);
    }

}
