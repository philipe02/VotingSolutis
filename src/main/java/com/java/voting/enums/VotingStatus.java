package com.java.voting.enums;

public enum VotingStatus {
    OPEN(0),
    VOTING(1),
    CLOSED(2);

    final Integer statusId;

    VotingStatus (Integer statusId){
        this.statusId = statusId;
    }

}
