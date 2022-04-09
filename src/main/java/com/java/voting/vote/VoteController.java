package com.java.voting.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vote")
public class VoteController {

    @Autowired
    VoteService service;

    @GetMapping("/v1")
    public ResponseEntity<List<Vote>> getAllVotes(){
        return new ResponseEntity<>(service.getAllVotes(), HttpStatus.OK);
    }

    @PostMapping("/v1")
    public ResponseEntity<Object> saveVote(@RequestBody Vote vote){
        return new ResponseEntity<>(service.saveVote(vote), HttpStatus.CREATED);
    }
}
