package com.java.voting.utils;

public class VotingUtils {
    private VotingUtils(){}

    public static Double ratioCalculator(Double total, Double value){
        return (value/total)*100;
    }
}
