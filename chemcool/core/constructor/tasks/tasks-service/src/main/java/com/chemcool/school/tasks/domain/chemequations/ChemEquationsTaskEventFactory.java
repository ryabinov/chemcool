package com.chemcool.school.tasks.domain.chemequations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ChemEquationsTaskEventFactory {
    public static ChemEquationsTaskEvent createTaskEvent(ChemEquationsTask task, ChemEquationsTaskEventType eventType){
        log.info("Попытка создать событие");
        return ChemEquationsTaskEvent.createEvent(task, eventType);
    }
}
