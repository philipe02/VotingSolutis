package com.java.voting.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.java.voting.enums.VotingStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity @Table(name = "voting")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Voting {

    public Voting (Topic topic){
        this.topic = topic;
        this.positiveVotes = 0;
        this.negativeVotes = 0;
        this.status = VotingStatus.OPEN;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_voting", nullable = false)
    private Long idVoting;

    @OneToOne
    @JoinColumn(name = "id_topic", referencedColumnName = "id_topic")
    private Topic topic;

    private Integer positiveVotes;

    private Integer negativeVotes;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Transient @JsonIgnore @OneToMany(mappedBy = "voting")
    private List<Vote> votes;

    @Transient @JsonIgnore @OneToOne(mappedBy = "voting")
    private VotingResults votingResults;

    @Enumerated(EnumType.ORDINAL)
    private VotingStatus status;

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;

        Voting vote = (Voting) o;

        return Objects.equals(this.idVoting, vote.getIdVoting())
                && Objects.equals(this.topic, vote.getTopic())
                && Objects.equals(this.startTime, vote.getStartTime())
                && Objects.equals(this.endTime, vote.getEndTime())
                && Objects.equals(this.status, vote.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVoting, topic, startTime, endTime, status);
    }
}