package com.java.voting;

import com.java.voting.controller.TopicController;
import com.java.voting.controller.VoteController;
import com.java.voting.controller.VotingController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VotingApplicationTests {

	@Autowired
	TopicController topicController;
	@Autowired
	VoteController voteController;
	@Autowired
	VotingController votingController;

	@Test
	void contextLoads(){
		Assertions.assertNotNull(topicController);
		Assertions.assertNotNull(voteController);
		Assertions.assertNotNull(votingController);
	}
}
