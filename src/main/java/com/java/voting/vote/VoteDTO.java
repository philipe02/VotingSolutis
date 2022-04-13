package com.java.voting.vote;

import com.java.voting.associate.AssociateDTO;
import com.java.voting.utils.VotingUtils;
import com.java.voting.voting.VotingDTO;
import lombok.Builder;

public record VoteDTO(Long idVote, AssociateDTO associate, VotingDTO voting, String votingTime, Boolean inFavour) {

    @Builder
    public VoteDTO{}

    public static VoteDTO createVoteDTO(Vote vote){
        return VoteDTO.builder()
                .idVote(vote.getIdVote())
                .associate(AssociateDTO.createAssociateDTO(vote.getAssociate()))
                .voting(VotingDTO.createVotingDTO(vote.getVoting()))
                .votingTime(VotingUtils.dateTimeFormatter(vote.getVotingTime()))
                .inFavour(vote.getInFavour())
                .build();
    }
}
