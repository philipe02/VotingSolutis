package com.java.voting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidCpfException extends ResponseStatusException {

    public InvalidCpfException(String message){
        super(HttpStatus.BAD_REQUEST, message);
    }
}
