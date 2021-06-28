package com.chemcool.school.lesson.theory.service;


import com.chemcool.school.lesson.theory.domain.ChemTheoryEvent;

public interface ChemTheoryEventNotificationService {
    void send(ChemTheoryEvent event);
}
