package com.chemcool.school.registration.service;

import com.chemcool.school.registration.domain.RegisterUser;
import com.chemcool.school.registration.repository.RegisterUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class RegisterUserServiceImpl implements RegisterUserService {

    private final RegisterUserRepository registerUserRepository;

    @Autowired
    public RegisterUserServiceImpl(RegisterUserRepository registerUserRepository) {
        this.registerUserRepository = registerUserRepository;
    }

    @Transactional
    @Override
    public void save(RegisterUser registerUser) {

        registerUserRepository.save(registerUser);
        log.info("Добавлен пользователь с UUID = " + registerUser.getId());
    }
}
