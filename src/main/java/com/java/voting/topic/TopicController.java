package com.java.voting.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
