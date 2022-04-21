package com.java.voting.model.dto;

import com.java.voting.utils.VotingUtils;
import com.java.voting.model.entity.Vote;
import lombok.Builder;

import java.util.Objects;

public record VoteDTO(Long idVote, AssociateDTO associate, VotingDTO voting, String votingTime, Boolean inFavour) {

    @Builder
    public VoteDTO{
        //Construtor vazio para utilizar o builder do Lombok em um record
    }

    public static VoteDTO createVoteDTO(Vote vote){
        return VoteDTO.builder()
                .idVote(vote.getIdVote())
                .associate(AssociateDTO.createAssociateDTO(vote.getAssociate()))
                .voting(VotingDTO.createVotingDTO(vote.getVoting()))
                .votingTime(VotingUtils.dateTimeFormatter(vote.getVotingTime()))
                .inFavour(vote.getInFavour())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VoteDTO voteDTO = (VoteDTO) o;

        return Objects.equals(idVote, voteDTO.idVote)
                && Objects.equals(associate, voteDTO.associate)
                && Objects.equals(voting, voteDTO.voting)
                && Objects.equals(votingTime, voteDTO.votingTime)
                && Objects.equals(inFavour, voteDTO.inFavour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVote, associate, voting, votingTime, inFavour);
    }
}
