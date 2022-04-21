package com.java.voting.controller;

import com.java.voting.model.dto.VoteDTO;
import com.java.voting.model.form.VoteForm;
import com.java.voting.service.VoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vote")
@Tag(name = "Vote")
public class VoteController {

    @Autowired
    VoteService service;

    @Operation(summary = "Return a list with all votes registered")
    @GetMapping("/v1")
    public ResponseEntity<List<VoteDTO>> getAllVotes(){
        return new ResponseEntity<>(service.getAllVotes(), HttpStatus.OK);
    }

    @Operation(summary = "Register a vote from an associate to the informed voting")
    @PostMapping("/v1")
    public ResponseEntity<VoteDTO> saveVote(@RequestBody VoteForm vote){
        return new ResponseEntity<>(service.saveVote(vote), HttpStatus.CREATED);
    }
}
