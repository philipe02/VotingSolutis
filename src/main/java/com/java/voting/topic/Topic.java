package com.java.voting.topic;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity @Table(name = "topic")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Topic {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_topic", nullable = false)
    private Long idTopic;

    @Column(nullable = false)
    private String title;

    private String description;

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;

        Topic vote = (Topic) o;

        return Objects.equals(this.idTopic, vote.getIdTopic())
                && Objects.equals(this.title, vote.getTitle())
                && Objects.equals(this.description, vote.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTopic, title, description);
    }
}