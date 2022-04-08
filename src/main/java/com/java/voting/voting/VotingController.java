package com.java.voting.voting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/voting")
public class VotingController {

    @Autowired
    VotingService service;

    @GetMapping("/v1/{id}")
    public ResponseEntity<Voting> getVotingById(@PathVariable("id") Long idVoting){
        return new ResponseEntity<>(service.getVotingById(idVoting), HttpStatus.OK);
    }

    @PostMapping("/v1/{topic}")
    public ResponseEntity<Voting> startVoting(@PathVariable("topic") Long idTopic, @RequestParam(name="duration", defaultValue = "60") Integer durationInSeconds){
        return new ResponseEntity<Voting>(service.startVoting(idTopic, durationInSeconds), HttpStatus.CREATED);
    }
}
