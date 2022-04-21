package com.java.voting.model.entity;

import com.java.voting.model.entity.Voting;
import lombok.*;

import javax.persistence.*;

@Entity @Table(name = "votingResults")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class VotingResults {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVotingResult;

    @OneToOne
    @JoinColumn(name = "id_voting", referencedColumnName = "id_voting")
    private Voting voting;

    private String result;

    private Double positiveRatio;

    private Double negativeRatio;

}
