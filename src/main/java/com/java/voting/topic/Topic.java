package com.java.voting.topic;

import lombok.*;

import javax.persistence.*;

@Entity @Table(name = "topic")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Topic {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_topic", nullable = false)
    private Long idTopic;

    private String title;

}