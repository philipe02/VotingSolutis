package com.java.voting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class VotingClosedException extends ResponseStatusException {

    public VotingClosedException(String message){
        super(HttpStatus.BAD_REQUEST, message);
    }
}
