package com.chemcool.school.auth.service.service;

import com.chemcool.school.auth.service.domain.RegisterUserEvent;
import com.chemcool.school.auth.service.exeption.RegisterUserDefinitionException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegisterUserEventNotificationServiceImpl implements RegisterUserEventNotificationService {
    private final UserRegistrationEventService userRegistrationEventService;
    private final KafkaTemplate<String, RegisterUserEvent> kafkaTemplate;

    private static final String REGISTRATION_USERS_SOCIAL = "registration-users-social";

    @Override
    public void send(RegisterUserEvent event) {
        ListenableFuture<SendResult<String, RegisterUserEvent>> future = kafkaTemplate.send(REGISTRATION_USERS_SOCIAL,
                UUID.randomUUID().toString(), event);

        userRegistrationEventService.saveEvent(event);

        if (future.isCancelled()) {
            throw new RegisterUserDefinitionException("Произошла ошибка при записи в кафку");
        }
        kafkaTemplate.flush();
    }
}
