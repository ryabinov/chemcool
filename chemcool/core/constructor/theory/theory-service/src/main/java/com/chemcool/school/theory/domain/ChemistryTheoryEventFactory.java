package com.chemcool.school.theory.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ChemistryTheoryEventFactory {
    public static ChemistryTheoryEvent createEvent(ChemistryTheory theory, ChemistryTheoryEventType eventType){
        log.info("Попытка создать событие {} при создании теории", eventType);
        return ChemistryTheoryEvent.createEvent(theory,eventType);
    }
}
