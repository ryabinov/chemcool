package com.chemcool.school.registration.service;

import com.chemcool.school.registration.domain.RegisterUserEvent;

public interface RegisterUserEventNotificationService {
    void send(RegisterUserEvent event);
}
