package com.java.voting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class VotingAlreadyExistsException extends ResponseStatusException {

    public VotingAlreadyExistsException(String message){
        super(HttpStatus.BAD_REQUEST, message);
    }
}
