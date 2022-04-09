package com.java.voting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class VoteAlreadyRegistered extends ResponseStatusException {

    public VoteAlreadyRegistered(String message){
        super(HttpStatus.BAD_REQUEST, message);
    }
}
