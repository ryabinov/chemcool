package com.chemcool.school.lesson.domain.singleselect;


import com.chemcool.school.lesson.exception.singleselect.ChemSingleSelectTaskEventException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ChemSingleSelectTaskEventFactory {

    public static ChemSingleSelectTaskEvent createEvent(ChemSingleSelectTask task, ChemTaskEventType eventType){

        if (false) {
            //todo реализовать логику для фабрики события создания таски
            throw new ChemSingleSelectTaskEventException("Что-то не получилось для создания ивента");
        }
        log.info("Попытка создать событие {} при создании таски с ВЫБОРОМ ОДНОГО ОТВЕТА ИЗ МНОЖЕСТВА", eventType);
        return ChemSingleSelectTaskEvent.createEvent(task, "777", eventType);
    }
}
