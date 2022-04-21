package com.java.voting.model.dto;

import com.java.voting.utils.VotingUtils;
import com.java.voting.model.entity.Voting;
import com.java.voting.enums.VotingStatus;
import lombok.Builder;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VotingDTO votingDTO = (VotingDTO) o;

        return Objects.equals(idVoting, votingDTO.idVoting)
                && Objects.equals(topic, votingDTO.topic)
                && Objects.equals(startTime, votingDTO.startTime)
                && Objects.equals(endTime, votingDTO.endTime)
                && status == votingDTO.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVoting, topic, startTime, endTime, status);
    }
}
