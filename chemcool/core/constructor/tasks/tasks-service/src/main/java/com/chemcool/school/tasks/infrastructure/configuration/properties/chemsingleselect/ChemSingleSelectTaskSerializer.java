package com.chemcool.school.tasks.infrastructure.configuration.properties.chemsingleselect;


import com.chemcool.school.tasks.domain.chemsingleselect.ChemSingleSelectTaskEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

@Slf4j
public class ChemSingleSelectTaskSerializer implements Serializer<ChemSingleSelectTaskEvent> {

    private final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    @Override
    public void configure(Map<String, ?> map, boolean isKey) {
    }

    @Override
    public byte[] serialize(String s, ChemSingleSelectTaskEvent event) {
        try {
            return mapper.writeValueAsBytes(event);
        } catch (JsonProcessingException exception) {
            log.error("Unable to serialize object {}", event, exception);
            return null;
        }
    }

    @Override
    public void close() {
    }
}
