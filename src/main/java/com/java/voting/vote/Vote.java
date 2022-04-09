package com.java.voting.vote;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity @Table(name = "vote")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Vote {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vote", nullable = false)
    private Long idVote;

    private Long idAssociate;

    private Long idVoting;

    private LocalDateTime votingTime;

    private Boolean inFavour;

}