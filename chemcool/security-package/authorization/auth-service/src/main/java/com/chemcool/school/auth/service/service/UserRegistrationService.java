package com.chemcool.school.auth.service.service;

import com.chemcool.school.auth.service.domain.RegisterUser;

public interface UserRegistrationService {
    void save(RegisterUser registerUser);
}
