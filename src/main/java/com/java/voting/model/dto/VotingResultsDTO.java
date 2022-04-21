package com.java.voting.model.dto;

import com.java.voting.model.entity.VotingResults;
import lombok.Builder;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VotingResultsDTO votingResultsDTO = (VotingResultsDTO) o;

        return Objects.equals(idVotingResult, votingResultsDTO.idVotingResult)
                && Objects.equals(voting, votingResultsDTO.voting)
                && Objects.equals(result, votingResultsDTO.result)
                && Objects.equals(positiveRatio, votingResultsDTO.positiveRatio)
                && Objects.equals(negativeRatio, votingResultsDTO.negativeRatio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVotingResult, voting, result, positiveRatio, negativeRatio);
    }
}
