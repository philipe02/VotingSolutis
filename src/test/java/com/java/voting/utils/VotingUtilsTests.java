package com.java.voting.utils;

import com.java.voting.voting.Voting;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VotingUtilsTests {

    @Test
    void ratioCalculator(){
        Assertions.assertEquals(75.0, VotingUtils.ratioCalculator(4.0, 3.0));
    }

    @Test
    void getResultInFavour(){
        Voting voting = Voting.builder().positiveVotes(3).negativeVotes(1).build();

        Assertions.assertEquals("In favour", VotingUtils.getResult(voting));
    }

    @Test
    void getResultDraw(){
        Voting voting = Voting.builder().positiveVotes(2).negativeVotes(2).build();

        Assertions.assertEquals("Draw", VotingUtils.getResult(voting));
    }

    @Test
    void getResultAgainst(){
        Voting voting = Voting.builder().positiveVotes(1).negativeVotes(3).build();

        Assertions.assertEquals("Against", VotingUtils.getResult(voting));
    }
}
