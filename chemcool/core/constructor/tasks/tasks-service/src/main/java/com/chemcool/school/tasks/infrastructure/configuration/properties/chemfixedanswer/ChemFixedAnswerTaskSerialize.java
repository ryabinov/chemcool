package com.chemcool.school.tasks.infrastructure.configuration.properties.chemfixedanswer;



import com.chemcool.school.tasks.domain.chemfixedanswer.ChemFixedAnswerTaskEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

@Slf4j
public class ChemFixedAnswerTaskSerialize implements Serializer<ChemFixedAnswerTaskEvent> {

    private final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String s, ChemFixedAnswerTaskEvent event) {
        try{
            return mapper.writeValueAsBytes(event);
        } catch (JsonProcessingException exception){
            log.error("Unable to serialize object {}", event, exception);
            return null;
        }
    }

    @Override
    public void close() {
    }
}
