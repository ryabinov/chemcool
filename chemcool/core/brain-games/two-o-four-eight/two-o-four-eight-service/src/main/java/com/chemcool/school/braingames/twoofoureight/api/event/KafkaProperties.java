package com.chemcool.school.braingames.twoofoureight.api.event;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("kafka")
public class KafkaProperties {
    private String server;
    private String groupId;
}
