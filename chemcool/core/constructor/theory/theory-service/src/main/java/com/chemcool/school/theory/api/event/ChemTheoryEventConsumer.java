package com.chemcool.school.theory.api.event;

import com.chemcool.school.theory.domain.ChemistryTheoryEvent;
import com.chemcool.school.theory.service.ChemistryTheoryEventService;
import com.chemcool.school.theory.service.ChemistryTheoryPageService;
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

    private final ChemistryTheoryPageService theoryPageService;
    private final ChemistryTheoryEventService eventService;

    @KafkaHandler
    @KafkaListener(topics = "chemistry-theory")
    @Transactional
    public void handleChemistryTheory(ConsumerRecord<String, ChemistryTheoryEvent> record){
        ChemistryTheoryEvent event = record.value();
        log.info("Пойман журнал для логгирования: " + event.getEventId());
        eventService.handleEvent(event);
        theoryPageService.save(event.getEventPayload());
    }
}
