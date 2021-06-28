package com.chemcool.school.registration.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RegisterUserFactory {

    public static RegisterUser createUser(RegisterUserExample registerUserExample) {
        log.info("Попытка зарегистрировать пользователя");
        return RegisterUser.createUser(registerUserExample);
    }
}
