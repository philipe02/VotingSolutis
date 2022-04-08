package com.java.voting.voting;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Table(name = "voting")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Voting {

    public Voting (Long idTopic){
        this.idTopic = idTopic;
        this.positiveVotes = 0;
        this.negativeVotes = 0;
        this.status = "voting";
        this.startTime = LocalDateTime.now();
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_voting", nullable = false)
    private Long idVoting;

    private Long idTopic;

    private Integer positiveVotes;

    private Integer negativeVotes;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String status;

}