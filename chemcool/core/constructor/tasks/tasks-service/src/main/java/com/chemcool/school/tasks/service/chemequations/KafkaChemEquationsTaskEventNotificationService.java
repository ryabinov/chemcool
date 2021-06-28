package com.chemcool.school.tasks.service.chemequations;


import com.chemcool.school.tasks.domain.chemequations.ChemEquationsTaskEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.UUID;

/**
 * Реализация уведомления о событии с соединениями
 *
 * @version 1.0
 * @autor Евгений Жиленков
 */
@Slf4j
@Service
@AllArgsConstructor
public class KafkaChemEquationsTaskEventNotificationService implements ChemEquationsTaskEventNotificationService {

    private final KafkaTemplate<String, ChemEquationsTaskEvent> chemEquationsTaskEventKafkaTemplate;

    @Override
    public void send(ChemEquationsTaskEvent event) {
        ListenableFuture<SendResult<String, ChemEquationsTaskEvent>> future = chemEquationsTaskEventKafkaTemplate.send("task-chemistry-equations", UUID.randomUUID().toString(), event);
        future.addCallback(System.out::println, System.out::println);
        chemEquationsTaskEventKafkaTemplate.flush();
    }
}
