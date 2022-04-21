package com.java.voting.model.dto;

import com.java.voting.model.entity.Topic;
import lombok.Builder;

import java.util.Objects;

public record TopicDTO( Long idTopic, String title, String description) {
    @Builder
    public TopicDTO{
        //Construtor vazio para utilizar o builder do Lombok em um record
    }

    public static TopicDTO createTopicDTO(Topic topic){
        return TopicDTO.builder()
                .idTopic(topic.getIdTopic())
                .title(topic.getTitle())
                .description(topic.getDescription())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopicDTO topicDTO = (TopicDTO) o;
        return Objects.equals(idTopic, topicDTO.idTopic)
                && Objects.equals(title, topicDTO.title)
                && Objects.equals(description, topicDTO.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTopic, title, description);
    }
}
