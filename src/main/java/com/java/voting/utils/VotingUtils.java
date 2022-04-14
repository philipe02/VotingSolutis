package com.java.voting.utils;

import com.java.voting.voting.Voting;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class VotingUtils {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss:SS");

    public static Double ratioCalculator(Double total, Double value){
        return (value/total)*100;
    }

    public static String getResult(Voting voting) {
        if (voting.getPositiveVotes() > voting.getNegativeVotes())
            return "In favour";
        if (voting.getPositiveVotes().equals(voting.getNegativeVotes()))
            return "Draw";
        return "Against";
    }

    public static String dateTimeFormatter(LocalDateTime dateTime){
        if(dateTime == null) return null;
        return dateTime.format(dateTimeFormatter);
    }
}
