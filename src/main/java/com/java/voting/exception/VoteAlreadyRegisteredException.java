package com.java.voting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class VoteAlreadyRegisteredException extends ResponseStatusException {

    public VoteAlreadyRegisteredException(String message){
        super(HttpStatus.BAD_REQUEST, message);
    }
}
