package com.chemcool.school.lesson.service.matches;

import com.chemcool.school.lesson.domain.matches.ChemMatchingTaskEvent;
import com.chemcool.school.lesson.exception.matches.ChemMatchingTaskException;
import com.chemcool.school.lesson.service.matches.ChemMatchingTaskEventNotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.UUID;


@Slf4j
@Service
@AllArgsConstructor
public class KafkaChemMatchingTaskEventNotificationService implements ChemMatchingTaskEventNotificationService {

    private final KafkaTemplate<String, ChemMatchingTaskEvent> kafkaTemplate;
    private static final String TOPIC = "tasks-matching";

    @Override
    public void send(ChemMatchingTaskEvent event) {
        ListenableFuture<SendResult<String, ChemMatchingTaskEvent>> future = kafkaTemplate.send(TOPIC, UUID.randomUUID().toString(), event);
        if (future.isCancelled()) {
            throw new ChemMatchingTaskException("Произошла ошибка при записи в кафку");
        }
        kafkaTemplate.flush();
    }
}
/*
  private final KafkaTemplate<String, ChemFixedAnswerTaskEvent> kafkaTemplate;

    @Override
    public void send(ChemFixedAnswerTaskEvent event) {
        ListenableFuture<SendResult<String, ChemFixedAnswerTaskEvent>> future = kafkaTemplate.send("fixed-answer", UUID.randomUUID().toString(), event);
        future.addCallback(System.out::println, System.out::println);
        kafkaTemplate.flush();
    }
 */