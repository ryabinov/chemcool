package com.chemcool.school.lesson.api.event;

import com.chemcool.school.lesson.theory.domain.ChemTheoryEvent;
import com.chemcool.school.lesson.theory.service.ChemTheoryEventService;
import com.chemcool.school.lesson.theory.service.ChemTheoryPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@EnableKafka
@RequiredArgsConstructor
public class ChemTheoryEventConsumer {

    private final ChemTheoryPageService theoryPageService;
    private final ChemTheoryEventService eventService;

    @KafkaHandler
    @KafkaListener(topics = "chemistry-theory", containerFactory = "theoryKafkaListenerContainerFactory")
    @Transactional
    public void handleChemistryTheory(ConsumerRecord<String, ChemTheoryEvent> record){
        ChemTheoryEvent event = record.value();
        log.info("Пойман журнал для логгирования: " + event.getEventId());
        eventService.handleEvent(event);
        theoryPageService.save(event.getEventPayload());
    }
}

