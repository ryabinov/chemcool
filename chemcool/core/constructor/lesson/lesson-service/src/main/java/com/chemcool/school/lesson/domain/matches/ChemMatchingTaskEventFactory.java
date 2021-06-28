package com.chemcool.school.lesson.domain.matches;

import com.chemcool.school.lesson.exception.matches.ChemMatchingTaskException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ChemMatchingTaskEventFactory {

    public static ChemMatchingTaskEvent createEvent(ChemMatchingTask task, ChemMatchingTaskEventType eventType){

        if (false) {
            //todo реализовать логику для фабрики события создания таски
            throw new ChemMatchingTaskException("Что-то не получилось для создания ивента");
        }
        log.info("Попытка создать событие {} при создании таски с СОПОСТАВЛЕНИЕМ", eventType);
        return ChemMatchingTaskEvent.createEvent(task, "777", eventType);
    }
}