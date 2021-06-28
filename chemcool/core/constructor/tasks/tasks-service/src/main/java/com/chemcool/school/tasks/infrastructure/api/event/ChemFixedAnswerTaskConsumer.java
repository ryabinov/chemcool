package com.chemcool.school.tasks.infrastructure.api.event;


import com.chemcool.school.tasks.domain.chemfixedanswer.ChemFixedAnswerTaskEvent;
import com.chemcool.school.tasks.service.chemfixedanswer.ChemFixedAnswerTaskEventService;
import com.chemcool.school.tasks.service.chemfixedanswer.ChemFixedAnswerTaskService;
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
public class ChemFixedAnswerTaskConsumer {

    private final ChemFixedAnswerTaskService taskService;
    private final ChemFixedAnswerTaskEventService eventService;

    @KafkaListener(topics = "task-chemistry-answer", containerFactory = "kafkaChemFixedAnswerListenerContainerFactory")
    @KafkaHandler
    public void handleChemFixedAnswerTask(ConsumerRecord<String, ChemFixedAnswerTaskEvent> record) {
        ChemFixedAnswerTaskEvent event = record.value();
        log.info("Пойман журнал для логирования с ID: " + event.getEventId());
        eventService.handleEvent(event);
        taskService.save(event.getEventPayload());
    }
}
