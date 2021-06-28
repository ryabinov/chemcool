package com.chemcool.school.lesson.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("kafka")
public class KafkaProperties {

    private String server;
    private String chemistryLessonGroupId;
    private String chemFixedAnswerGroupId;
    private String chemSingleSelectGroupId;
    private String theoryGroupId;
    private String chemEquationsGroupId;
    private String chemMatchesGroupId;
}
