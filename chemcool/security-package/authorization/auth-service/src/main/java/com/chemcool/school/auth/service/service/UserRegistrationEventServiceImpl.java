package com.chemcool.school.auth.service.service;

import com.chemcool.school.auth.service.domain.RegisterUserEvent;
import com.chemcool.school.auth.service.storage.RegisterUserEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegistrationEventServiceImpl implements UserRegistrationEventService {
    private final RegisterUserEventRepository registerUserEventRepository;

    public void saveEvent(RegisterUserEvent event) {
        registerUserEventRepository.save(event);
    }
}
