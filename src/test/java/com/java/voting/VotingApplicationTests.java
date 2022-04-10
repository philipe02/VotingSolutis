package com.java.voting;

import com.java.voting.topic.TopicController;
import com.java.voting.vote.VoteController;
import com.java.voting.voting.VotingController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;

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

	@TestConfiguration
	public static class Config {
		@Bean
		public Clock clock(){
			return Clock.fixed(Instant.parse("2021-09-10T12:00:00Z"), ZoneOffset.UTC);
		}
	}
}
