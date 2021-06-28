package com.chemcool.school.lesson.api.event;

import com.chemcool.school.lesson.domain.equation.ChemEquationsTaskEvent;
import com.chemcool.school.lesson.service.equation.ChemEquationsTaskEventService;
import com.chemcool.school.lesson.service.equation.ChemEquationsTaskService;
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
public class ChemEquationEventConsumer {
    private final ChemEquationsTaskEventService eventService;
    private final ChemEquationsTaskService taskService;

    @KafkaListener(topics = "equations-task", containerFactory = "chemEquationsKafkaListenerContainerFactory")
    @KafkaHandler
    public void handleChemEquationsTask(ConsumerRecord<String, ChemEquationsTaskEvent> record) {
        ChemEquationsTaskEvent event = record.value();
        log.info("Пойман журнал для логирования: " + event.getEventId());
        eventService.handleEvent(event);
        taskService.save(event.getEventPayload());
    }

}
