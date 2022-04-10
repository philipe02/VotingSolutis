package com.java.voting.topic;

import com.java.voting.utils.VotingUtils;
import com.java.voting.voting.Voting;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TopicViewModel {
    private Long idTopic;

    private String title;

    private String description;

    private String result;

    private Integer positiveVotes;

    private Integer negativeVotes;

    private Double positiveRatio;

    private Double negativeRatio;

    public static TopicViewModel createTopicViewModel(Topic topic, Voting voting){
        return TopicViewModel.builder()
                .idTopic(topic.getIdTopic())
                .title(topic.getTitle())
                .description(topic.getDescription())
                .positiveVotes(voting.getPositiveVotes())
                .negativeVotes(voting.getNegativeVotes())
                .result(
                        voting.getPositiveVotes() > voting.getNegativeVotes()
                                ? "In favour"
                                : voting.getPositiveVotes().equals(voting.getNegativeVotes())
                                ? "Draw"
                                : "Against"
                )
                .positiveRatio(
                        voting.getPositiveVotes() != null ?
                                VotingUtils.ratioCalculator(
                                        voting.getPositiveVotes().doubleValue()+voting.getNegativeVotes().doubleValue(),
                                        voting.getPositiveVotes().doubleValue()
                                ) : null
                ).negativeRatio(
                        voting.getNegativeVotes() != null ?
                                VotingUtils.ratioCalculator(
                                        voting.getPositiveVotes().doubleValue()+voting.getNegativeVotes().doubleValue(),
                                        voting.getNegativeVotes().doubleValue()
                                ) : null
                ).build();
    }

}
