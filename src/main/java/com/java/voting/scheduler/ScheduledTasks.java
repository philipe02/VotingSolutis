package com.java.voting.scheduler;

import com.java.voting.messenger.MessageSender;
import com.java.voting.utils.VotingUtils;
import com.java.voting.model.entity.Voting;
import com.java.voting.repository.VotingRepository;
import com.java.voting.model.entity.VotingResults;
import com.java.voting.repository.VotingResultsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ScheduledTasks {

    @Autowired
    MessageSender messageSender;
    @Autowired
    VotingRepository votingRepository;
    @Autowired
    VotingResultsRepository votingResultsRepository;

    @Scheduled(fixedRate = 60 * 1000)
    public void collectVotingResults(){
        log.info("Collecting results");

        List<Voting> votingToCollectList = votingRepository.findAllVotingEndedAndNotClosed();

        //Salva os VotingResults e fecha as votações correspondentes
        if (!votingToCollectList.isEmpty()) {
            votingToCollectList.stream()
                    .map(this::collectAndSaveResultsFromVoting)
                    .forEach(votingResults -> {
                        votingRepository.closeVoting(votingResults.getVoting().getIdVoting());
                        messageSender.send("Voting for " + votingResults.getVoting().getTopic().getTitle() + " collected. Result: " + votingResults.getResult());
                    });
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
