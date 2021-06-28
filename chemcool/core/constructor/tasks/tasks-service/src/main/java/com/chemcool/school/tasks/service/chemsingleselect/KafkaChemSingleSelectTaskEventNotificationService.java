package com.chemcool.school.tasks.service.chemsingleselect;

import com.chemcool.school.tasks.ChemSingleSelectDefinitionException;
import com.chemcool.school.tasks.domain.chemsingleselect.ChemSingleSelectTaskEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class KafkaChemSingleSelectTaskEventNotificationService implements ChemSingleSelectTaskEventNotificationService {

    private final KafkaTemplate<String, ChemSingleSelectTaskEvent> chemSingleSelectTaskEventKafkaTemplate;
    private static final String TOPIC = "task-chemistry-singleSelect";

    @Override
    public void send(ChemSingleSelectTaskEvent event) {
        ListenableFuture<SendResult<String, ChemSingleSelectTaskEvent>> future = chemSingleSelectTaskEventKafkaTemplate.send(TOPIC, UUID.randomUUID().toString(), event);
        if (future.isCancelled()) {
            throw new ChemSingleSelectDefinitionException("Произошла ошибка при записи в кафку");
        }
        chemSingleSelectTaskEventKafkaTemplate.flush();
    }
}
