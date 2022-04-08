package com.java.voting.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    TopicService service;

    @GetMapping("/v1/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable("id") Long id){
        return new ResponseEntity<Topic>(service.getTopicById(id), HttpStatus.OK) ;
    }

    @PostMapping("/v1")
    public ResponseEntity<Topic> saveTopic(@RequestBody Topic topic){
        return new ResponseEntity<Topic>(service.saveTopic(topic), HttpStatus.CREATED);
    }
}
