package com.java.voting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class VotingAlreadyExists extends ResponseStatusException {

    public VotingAlreadyExists(String message){
        super(HttpStatus.BAD_REQUEST, message);
    }
}
