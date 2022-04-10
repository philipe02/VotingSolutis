package com.java.voting.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VotingUtilsTests {

    @Test
    void ratioCalculator(){
        Assertions.assertEquals(75.0, VotingUtils.ratioCalculator(4.0, 3.0));
    }
}
