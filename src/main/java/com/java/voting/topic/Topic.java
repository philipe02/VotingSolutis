package com.java.voting.topic;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "topic")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Topic {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_topic", nullable = false)
    private Long idTopic;

    @Column(nullable = false)
    private String title;

    private String description;

}