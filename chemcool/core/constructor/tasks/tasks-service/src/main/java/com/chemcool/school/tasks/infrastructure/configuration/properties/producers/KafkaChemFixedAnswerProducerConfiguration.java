package com.chemcool.school.tasks.infrastructure.configuration.properties.producers;


import com.chemcool.school.tasks.domain.chemfixedanswer.ChemFixedAnswerTaskEvent;
import com.chemcool.school.tasks.infrastructure.configuration.properties.chemfixedanswer.ChemFixedAnswerTaskSerialize;
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
public class KafkaChemFixedAnswerProducerConfiguration {

    private final KafkaProperties kafkaProperties;

    @Bean
    public Map<String, Object> chemFixedAnswerProducerConfig(){
        Map<String, Object> prop = new HashMap<>();
        prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getServer());
        prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ChemFixedAnswerTaskSerialize.class);
        return prop;
    }

    @Bean
    public ProducerFactory<String, ChemFixedAnswerTaskEvent> chemFixedAnswerTaskEventProducerFactory(){
        return new DefaultKafkaProducerFactory<>(chemFixedAnswerProducerConfig());
    }

    @Bean
    public KafkaTemplate<String, ChemFixedAnswerTaskEvent> chemFixedAnswerTaskEventKafkaTemplate(){
        return new KafkaTemplate<>(chemFixedAnswerTaskEventProducerFactory());
    }
}
