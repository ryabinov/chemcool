package com.chemcool.school.lesson.theory.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ChemTheoryEventFactory {
    public static ChemTheoryEvent createEvent(ChemTheory theory, ChemTheoryEventType eventType){
        log.info("Попытка создать событие {} при создании теории", eventType);
        return ChemTheoryEvent.createEvent(theory,eventType);
    }
}
