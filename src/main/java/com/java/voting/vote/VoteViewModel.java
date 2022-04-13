package com.java.voting.vote;

import lombok.Builder;

public record VoteViewModel(Long idAssociate, Long idVoting, Boolean inFavour) {

    @Builder
    public VoteViewModel{}

}
