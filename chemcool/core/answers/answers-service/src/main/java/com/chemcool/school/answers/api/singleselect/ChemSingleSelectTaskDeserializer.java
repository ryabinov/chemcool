package com.chemcool.school.answers.api.singleselect;

import com.chemcool.school.answers.domain.singleselect.ChemSingleSelectTaskEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
public class ChemSingleSelectTaskDeserializer implements Deserializer<ChemSingleSelectTaskEvent> {

    private final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    @Override
    public void configure(Map<String, ?> map, boolean b) {
    }

    @Override
    public ChemSingleSelectTaskEvent deserialize(String s, byte[] event) {
        try {
            return mapper.readValue(new String(event, StandardCharsets.UTF_8), ChemSingleSelectTaskEvent.class);
        } catch (Exception exception) {
            log.error("Unable to deserialize message {}", event, exception);
            return null;
        }
    }

    @Override
    public void close() {
    }

}
