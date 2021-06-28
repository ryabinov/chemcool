package com.chemcool.school.registration.web.api.service;

import com.chemcool.school.registration.domain.RegisterUserAccountRole;
import com.chemcool.school.registration.domain.RegisterUserAccountType;
import com.chemcool.school.registration.repository.RegisterUserRepository;
import com.chemcool.school.registration.web.api.dto.RegisterUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Slf4j
@Service
public class UtilUserService {

    private final RegisterUserRepository repository;

    public UtilUserService(RegisterUserRepository repository) {
        this.repository = repository;
    }

    public boolean checkMail(RegisterUserDto registerUserDto) {
        return repository.existsByEmail(registerUserDto.getEmail());

    }

    public boolean checkAge(RegisterUserDto registerUserDto) {
        return Period.between(registerUserDto.getBirthday(), LocalDate.now()).getYears() < 18;
    }

    public void checkAndSetRole(RegisterUserDto registerUserDto) {
        if (registerUserDto.getRole() == null) {
            registerUserDto.setRole(RegisterUserAccountRole.ROLE_USER_BASE);
        }
        if (registerUserDto.getType() == null) {
            registerUserDto.setType(RegisterUserAccountType.BASE);
        }
    }
}
