package com.chemcool.school.auth.service.service;

import com.chemcool.school.auth.service.domain.RegisterUserEvent;

public interface UserRegistrationEventService {
    void saveEvent(RegisterUserEvent event);
}
