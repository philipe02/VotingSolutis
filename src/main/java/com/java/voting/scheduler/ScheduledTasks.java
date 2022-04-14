package com.java.voting.scheduler;

import com.java.voting.utils.VotingUtils;
import com.java.voting.voting.Voting;
import com.java.voting.voting.VotingRepository;
import com.java.voting.voting.VotingResults;
import com.java.voting.voting.VotingResultsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class ScheduledTasks {

    @Autowired
    VotingRepository votingRepository;
    @Autowired
    VotingResultsRepository votingResultsRepository;

    @Scheduled(fixedRate = 60 * 1000)
    public void collectVotingResults(){
        log.info("Collecting results");

        List<Voting> votingToCollectList = votingRepository.findAllVotingEndedAndNotClosed();

        //Salva os VotingResults e fecha as votações correspondentes
        if(votingToCollectList.size() > 0) {
            votingToCollectList.stream()
                    .map(this::collectAndSaveResultsFromVoting)
                    .forEach(votingResults -> votingRepository.closeVoting(votingResults.getVoting().getIdVoting()));
        }
    }

    private VotingResults collectAndSaveResultsFromVoting(Voting voting){
        VotingResults votingResults = VotingResults.builder()
                .voting(voting)
                .positiveRatio(VotingUtils.ratioCalculator((double)voting.getPositiveVotes()+voting.getNegativeVotes(), (double) voting.getPositiveVotes()))
                .negativeRatio(VotingUtils.ratioCalculator((double)voting.getPositiveVotes()+voting.getNegativeVotes(), (double) voting.getNegativeVotes()))
                .result(VotingUtils.getResult(voting))
                .build();
        return votingResultsRepository.save(votingResults);
    }
}
