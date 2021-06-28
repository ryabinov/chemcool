package com.chemcool.school.auth.service.service;

import com.chemcool.school.auth.service.domain.RegisterUser;
import com.chemcool.school.auth.service.storage.RegisterUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegistrationServiceImpl implements UserRegistrationService{
    private final RegisterUserRepository registerUserRepository;

    @Override
    public void save(RegisterUser registerUser) {
        registerUserRepository.save(registerUser);
    }
}
