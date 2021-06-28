package com.chemcool.school.lesson.configuration.properties.singleselect;

import com.chemcool.school.lesson.domain.singleselect.ChemSingleSelectTaskEvent;
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
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public ChemSingleSelectTaskEvent deserialize(String topic, byte[] event) {
        try {
            return mapper.readValue(new String(event, StandardCharsets.UTF_8), ChemSingleSelectTaskEvent.class);
        } catch (Exception exception) {
            log.error("Невозможно десериализовать сообщение {}", event, exception);
            return null;
        }
    }

    @Override
    public void close() {
    }
}
