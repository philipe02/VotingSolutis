package com.java.voting.voting;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/voting")
@Tag(name = "Voting")
public class VotingController {

    @Autowired
    VotingService service;

    @Operation(summary = "Return Voting section of specified id")
    @GetMapping("/v1/{id}")
    public ResponseEntity<Voting> getVotingById(@PathVariable("id") Long idVoting){
        return new ResponseEntity<>(service.getVotingById(idVoting), HttpStatus.OK);
    }

    @Operation(summary = "Create Voting section of specified topic")
    @PostMapping("/v1/{topicId}")
    public ResponseEntity<Voting> createVoting(@PathVariable("topicId") Long idTopic){
        return new ResponseEntity<>(service.createVoting(idTopic), HttpStatus.CREATED);
    }

    @Operation(summary = "Start Voting section of specified id")
    @PostMapping("/v1/start/{votingId}")
    public ResponseEntity<String> startVoting(@PathVariable("votingId") Long idVoting, @RequestParam(name="duration", defaultValue = "20") Integer durationInSeconds){
        return new ResponseEntity<>(service.startVoting(idVoting, durationInSeconds), HttpStatus.OK);
    }
}