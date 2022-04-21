package com.java.voting.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity @Table(name = "vote")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Vote {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vote", nullable = false)
    private Long idVote;

    @ManyToOne
    @JoinColumn(name = "id_associate", referencedColumnName = "id_associate")
    private Associate associate;

    @ManyToOne
    @JoinColumn(name = "id_voting", referencedColumnName = "id_voting")
    private Voting voting;

    private LocalDateTime votingTime;

    private Boolean inFavour;

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;

        Vote vote = (Vote) o;

        return Objects.equals(this.idVote, vote.idVote)
                && Objects.equals(this.associate, vote.getAssociate())
                && Objects.equals(this.voting, vote.getVoting())
                && Objects.equals(this.inFavour, vote.getInFavour())
                && Objects.equals(this.votingTime, vote.getVotingTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVote, associate, voting, votingTime, inFavour);
    }
}