package com.java.voting.voting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Object> createVoting(@PathVariable("topic") Long idTopic){
        return new ResponseEntity<>(service.createVoting(idTopic), HttpStatus.CREATED);
    }

    @PostMapping("/v1/start/{voting}")
    public ResponseEntity<Object> startVoting(@PathVariable("voting") Long idVoting, @RequestParam(name="duration", defaultValue = "20") Integer durationInSeconds){
        return new ResponseEntity<Object>(service.startVoting(idVoting, durationInSeconds), HttpStatus.OK);
    }
}
