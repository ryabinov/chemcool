package com.chemcool.school.auth.service.config.properties;


import com.chemcool.school.auth.service.domain.RegisterUserEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
public class UserDeserializer implements Deserializer<RegisterUserEvent> {

    private final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public RegisterUserEvent deserialize(String s, byte[] event) {
        try {
            return mapper.readValue(new String(event, StandardCharsets.UTF_8), RegisterUserEvent.class);
        } catch (Exception exception) {
            log.error("Unable to deserializer massage {}", event, exception);
            return null;
        }
    }

    @Override
    public void close() {
    }
}
