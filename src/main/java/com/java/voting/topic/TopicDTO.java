package com.java.voting.topic;

import lombok.Builder;
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
}
