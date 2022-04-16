package com.java.voting.voting;

import com.java.voting.topic.TopicDTO;
import com.java.voting.utils.VotingUtils;
import lombok.Builder;

public record VotingDTO(Long idVoting, TopicDTO topic, Integer positiveVotes, Integer negativeVotes,Integer totalVotes, String startTime, String endTime, VotingStatus status) {

    @Builder
    public VotingDTO{
        //Construtor vazio para utilizar o builder do Lombok em um record
    }

    public static VotingDTO createVotingDTO (Voting voting){
        return VotingDTO.builder()
                .idVoting(voting.getIdVoting())
                .topic(TopicDTO.createTopicDTO(voting.getTopic()))
                .positiveVotes(voting.getPositiveVotes())
                .negativeVotes(voting.getNegativeVotes())
                .totalVotes(voting.getPositiveVotes() + voting.getNegativeVotes())
                .startTime(VotingUtils.dateTimeFormatter(voting.getStartTime()))
                .endTime(VotingUtils.dateTimeFormatter(voting.getEndTime()))
                .status(voting.getStatus())
                .build();
    }

}
