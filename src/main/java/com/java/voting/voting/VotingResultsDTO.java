package com.java.voting.voting;

import lombok.Builder;

public record VotingResultsDTO(Long idVotingResult, VotingDTO voting, String result, Double positiveRatio, Double negativeRatio) {

    @Builder
    public VotingResultsDTO{
        //Construtor vazio para utilizar o builder do Lombok em um record
    }

    public static VotingResultsDTO createVotingResultsDTO(VotingResults votingResults){
        return VotingResultsDTO.builder()
                .idVotingResult(votingResults.getIdVotingResult())
                .voting(VotingDTO.createVotingDTO(votingResults.getVoting()))
                .result(votingResults.getResult())
                .positiveRatio(votingResults.getPositiveRatio())
                .negativeRatio(votingResults.getNegativeRatio())
                .build();
    }
}
