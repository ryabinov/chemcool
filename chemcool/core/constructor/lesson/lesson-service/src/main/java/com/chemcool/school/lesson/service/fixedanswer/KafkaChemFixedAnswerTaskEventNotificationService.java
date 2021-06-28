package com.chemcool.school.lesson.service.fixedanswer;

import com.chemcool.school.lesson.domain.fixedanswer.ChemFixedAnswerTaskEvent;
import com.chemcool.school.lesson.service.fixedanswer.ChemFixedAnswerTaskEventNotificationService;
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
public class KafkaChemFixedAnswerTaskEventNotificationService implements ChemFixedAnswerTaskEventNotificationService {

    private final KafkaTemplate<String, ChemFixedAnswerTaskEvent> kafkaTemplate;

    @Override
    public void send(ChemFixedAnswerTaskEvent event) {
        ListenableFuture<SendResult<String, ChemFixedAnswerTaskEvent>> future = kafkaTemplate.send("fixed-answer", UUID.randomUUID().toString(), event);
        future.addCallback(System.out::println, System.out::println);
        kafkaTemplate.flush();
    }
}
