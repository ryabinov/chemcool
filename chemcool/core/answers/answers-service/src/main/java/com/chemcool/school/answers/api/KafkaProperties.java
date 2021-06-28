package com.chemcool.school.answers.api;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("kafka")

public class KafkaProperties {
    private String server;
    private String groupId1;
    private String groupId2;
    private String groupId3;
    private String groupId4;
}
