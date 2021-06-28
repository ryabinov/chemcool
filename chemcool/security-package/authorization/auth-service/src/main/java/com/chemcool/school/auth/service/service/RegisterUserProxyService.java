package com.chemcool.school.auth.service.service;

import com.chemcool.school.auth.service.domain.RegisterUser;
import com.chemcool.school.auth.service.domain.RegisterUserEventFactory;
import com.chemcool.school.auth.service.domain.RegisterUserEventType;
import com.chemcool.school.auth.service.storage.RegisterUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterUserProxyService {

    @Autowired
    private RegisterUserRepository userRepository;
    @Autowired
    private RegisterUserEventNotificationService registerUserEventNotificationService;

    public RegisterUser add(RegisterUser registerUser) {

        registerUserEventNotificationService.send(
                RegisterUserEventFactory.createEvent(registerUser, RegisterUserEventType.CREATE)
        );

        return userRepository.save(registerUser);

    }
}