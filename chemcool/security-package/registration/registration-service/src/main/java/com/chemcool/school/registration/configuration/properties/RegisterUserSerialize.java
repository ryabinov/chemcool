package com.chemcool.school.registration.configuration.properties;

import com.chemcool.school.registration.domain.RegisterUserEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

@Slf4j
public class RegisterUserSerialize implements Serializer<RegisterUserEvent> {

    private final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String topic, RegisterUserEvent event) {
        try {
            return mapper.writeValueAsBytes(event);
        } catch (JsonProcessingException exception) {
            log.error("Невозможно десериализовать сообщение {}", event, exception);
            return null;
        }
    }

    @Override
    public void close() {
    }
}
