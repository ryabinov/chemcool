package com.chemcool.school.lesson.api.event;

import com.chemcool.school.lesson.domain.matches.ChemMatchingTaskEvent;
import com.chemcool.school.lesson.service.matches.ChemMatchingTaskEventService;
import com.chemcool.school.lesson.service.matches.ChemMatchingTaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@Service
@EnableKafka
@EnableTransactionManagement
@RequiredArgsConstructor
public class ChemMatchesEventConsumer {


    private final ChemMatchingTaskEventService eventService;
    private final ChemMatchingTaskService taskService;

    @KafkaListener(topics = "tasks-matching", containerFactory = "chemMatchesKafkaListenerContainerFactory")
    @KafkaHandler
    public void handleChemMatchesTask(ConsumerRecord<String, ChemMatchingTaskEvent> record) {
        ChemMatchingTaskEvent event = record.value();
        log.info("Пойман журнал для логирования: " + event.getChemistryMatchingTaskEventId());
        eventService.handleEvent(event);
        taskService.save(event.getChemMatchingTaskEventPayload());
    }
}
