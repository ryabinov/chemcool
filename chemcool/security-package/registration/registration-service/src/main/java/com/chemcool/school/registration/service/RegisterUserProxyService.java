package com.chemcool.school.registration.service;

import com.chemcool.school.registration.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterUserProxyService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final RegisterUserEventNotificationService registerUserEventNotificationService;

    public String add(RegisterUserExample registerUserExample) {

        registerUserExample.setUserExamplePassword(passwordEncoder.encode(registerUserExample.getUserExamplePassword()));
        RegisterUser registerUser = RegisterUserFactory.createUser(registerUserExample);
        registerUserEventNotificationService.send(
                RegisterUserEventFactory.createEvent(registerUser, RegisterUserEventType.CREATE)
        );

        return registerUser.getId();

    }
}