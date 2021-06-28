package com.chemcool.school.auth.service.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RegisterUserEventFactory {

    public static RegisterUserEvent createEvent(RegisterUser registerUser, RegisterUserEventType registerUserEventType) {

        log.info("Попытка создать событие {} при регистрации пользователя через соц. сети ", registerUserEventType);
        return RegisterUserEvent.createEvent(registerUser, registerUserEventType);
    }
}
