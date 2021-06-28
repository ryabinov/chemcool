package com.chemcool.school.tasks.infrastructure.configuration.properties.producers;

import com.chemcool.school.tasks.domain.chemsingleselect.ChemSingleSelectTaskEvent;
import com.chemcool.school.tasks.infrastructure.configuration.properties.chemsingleselect.ChemSingleSelectTaskSerializer;
import com.chemcool.school.tasks.infrastructure.configuration.properties.KafkaProperties;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(KafkaProperties.class)
@RequiredArgsConstructor
public class KafkaChemSingleSelectProducerConfiguration {

    private final KafkaProperties kafkaProperties;

    @Bean
    public Map<String, Object> chemSingleSelectProducerConfig() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getServer());
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ChemSingleSelectTaskSerializer.class);
        return properties;
    }

    @Bean
    public ProducerFactory<String, ChemSingleSelectTaskEvent> chemSingleSelectTaskEventProducerFactory() {
        return new DefaultKafkaProducerFactory<>(chemSingleSelectProducerConfig());
    }

    @Bean
    public KafkaTemplate<String, ChemSingleSelectTaskEvent> chemSingleSelectTaskEventKafkaTemplate() {
        return new KafkaTemplate<>(chemSingleSelectTaskEventProducerFactory());
    }
}
