package com.chemcool.school.auth.service.api.event;

import com.chemcool.school.auth.service.domain.RegisterUserEvent;
import com.chemcool.school.auth.service.service.UserRegistrationEventService;
import com.chemcool.school.auth.service.service.UserRegistrationService;
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
public class UserConsumer {

    private final UserRegistrationEventService userRegistrationEventService;
    private final UserRegistrationService userRegistrationService;

    @KafkaListener(topics = "registration-users")
    @KafkaHandler
    public void orderListener(ConsumerRecord<String, RegisterUserEvent> record) {
        RegisterUserEvent event = record.value();
        log.info("Пойман журнал для логирования: {}", event.getEventId());
        event.setAuthorId(event.getPayload().getEmail());

        userRegistrationEventService.saveEvent(event);
        userRegistrationService.save(event.getPayload());
    }
}