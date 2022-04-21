package com.java.voting.model.form;

import lombok.Builder;

public record TopicForm(Long idTopic, String title, String description) {

    @Builder
    public TopicForm {
        //Construtor vazio para utilizar o builder do Lombok em um record
    }
}
