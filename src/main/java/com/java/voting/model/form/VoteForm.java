package com.java.voting.model.form;

import lombok.Builder;

public record VoteForm(Long idAssociate, Long idVoting, Boolean inFavour) {

    @Builder
    public VoteForm {
        //Construtor vazio para utilizar o builder do Lombok em um record
    }
}
