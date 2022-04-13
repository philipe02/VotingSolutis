package com.java.voting.topic;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topic")
@Tag(name = "Topic")
public class TopicController {

    @Autowired
    TopicService service;

    @Operation(summary = "Return topic of specified id")
    @GetMapping("/v1/{id}")
    public ResponseEntity<TopicDTO> getTopicById(@PathVariable("id") Long id){
        return new ResponseEntity<>(service.getTopicById(id), HttpStatus.OK) ;
    }

    @Operation(summary = "Create a new topic with informed values")
    @PostMapping("/v1")
    public ResponseEntity<Topic> saveTopic(@RequestBody Topic topic){
        return new ResponseEntity<>(service.saveTopic(topic), HttpStatus.CREATED);
    }
}
