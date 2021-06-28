package com.chemcool.school.registration.service;

import com.chemcool.school.registration.domain.RegisterUserEvent;
import com.chemcool.school.registration.repository.RegisterUserEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserEventService {
    private final RegisterUserEventRepository registerUserEventRepository;

    public void saveEvent(RegisterUserEvent event) {
        registerUserEventRepository.save(event);
    }
}
