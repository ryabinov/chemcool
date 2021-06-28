package com.chemcool.school.tasks.domain.chemfixedanswer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ChemFixedAnswerTaskEventFactory {
    public static ChemFixedAnswerTaskEvent createTaskEvent(ChemFixedAnswerTask task, ChemFixedAnswerTaskEventType eventType){
        log.info("Попытка создать событие");
        return ChemFixedAnswerTaskEvent.createEvent(task, eventType);
    }
}
