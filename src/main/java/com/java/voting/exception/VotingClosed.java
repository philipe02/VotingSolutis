package com.java.voting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class VotingClosed extends ResponseStatusException {

    public VotingClosed(String message){
        super(HttpStatus.BAD_REQUEST, message);
    }
}
