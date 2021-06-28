package com.chemcool.school.theory.service;

import com.chemcool.school.theory.domain.ChemistryTheoryEvent;

public interface ChemistryTheoryEventNotificationService {
    void send(ChemistryTheoryEvent event);
}
