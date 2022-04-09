package com.java.voting.voting;

import com.java.voting.topic.Topic;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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

    @Enumerated(EnumType.ORDINAL)
    private VotingStatus status;

}