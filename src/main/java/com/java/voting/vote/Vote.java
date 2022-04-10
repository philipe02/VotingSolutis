package com.java.voting.vote;

import javax.persistence.*;

import com.java.voting.topic.TopicViewModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;

        Vote vote = (Vote) o;

        return Objects.equals(this.idVote, vote.idVote)
                && Objects.equals(this.idAssociate, vote.getIdAssociate())
                && Objects.equals(this.idVoting, vote.getIdVoting())
                && Objects.equals(this.inFavour, vote.getInFavour())
                && Objects.equals(this.votingTime, vote.getVotingTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVote, idAssociate, idVoting, votingTime, inFavour);
    }
}