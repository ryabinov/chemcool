package com.chemcool.school.tasks.service.chemmatches;

import com.chemcool.school.tasks.ChemistryMatchingTaskException;
import com.chemcool.school.tasks.domain.chemmatches.ChemistryMatchingTaskEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class KafkaChemistryMatchingTaskEventNotificationService implements ChemistryMatchingTaskEventNotificationService {

    private final KafkaTemplate<String, ChemistryMatchingTaskEvent> chemistryMatchingTaskEventKafkaTemplate;
    private static final String TOPIC = "task-chemistry-matches";

    @Override
    public void send(ChemistryMatchingTaskEvent event) {
        ListenableFuture<SendResult<String, ChemistryMatchingTaskEvent>> future = chemistryMatchingTaskEventKafkaTemplate.send(TOPIC, UUID.randomUUID().toString(), event);
        if (future.isCancelled()) {
            throw new ChemistryMatchingTaskException("Произошла ошибка при записи в кафку");
        }
        chemistryMatchingTaskEventKafkaTemplate.flush();
    }
}
