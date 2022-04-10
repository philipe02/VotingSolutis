package com.java.voting.topic;

import com.java.voting.utils.VotingUtils;
import com.java.voting.voting.Voting;
import lombok.*;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;

        TopicViewModel topic = (TopicViewModel) o;

        return Objects.equals(this.idTopic, topic.idTopic)
                && Objects.equals(this.title, topic.getTitle())
                && Objects.equals(this.description, topic.getDescription())
                && Objects.equals(this.positiveVotes, topic.getPositiveVotes())
                && Objects.equals(this.negativeVotes, topic.getNegativeVotes())
                && Objects.equals(this.positiveRatio, topic.getPositiveRatio())
                && Objects.equals(this.negativeRatio, topic.getNegativeRatio());
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTopic, title, description, result, positiveVotes, negativeVotes, positiveRatio, negativeRatio);
    }
}
