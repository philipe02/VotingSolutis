package com.java.voting.vote;

import lombok.*;

import javax.persistence.*;

@Entity @Table(name = "vote")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Vote {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vote", nullable = false)
    private Long idVote;

    private Long idAssociate;

    private Long idVoting;

    private Integer option;

}