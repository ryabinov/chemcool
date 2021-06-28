package com.chemcool.school.lesson.domain.equation;

import com.chemcool.school.lesson.exception.equation.ChemEquationsTaskException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ChemEquationsTaskFactory {
    public static ChemEquationsTask createChemEquationsTask(ChemEquationsTaskExample example){

        if (example.getChapterId() < 1 || example.getChapterId() > 4) {
            throw new ChemEquationsTaskException("Ошибка! Поле \"Глава\" имеет значение от 1 до 4 ");
        }
        if (example.getReferenceId() < 1 || example.getReferenceId() > 4) {
            throw new ChemEquationsTaskException("Ошибка! Поле \"Раздел\" имеет значение от 1 до 4 ");
        }
        log.info("Попытка создать задание.");
        return ChemEquationsTask.createChemEquationsTask(example);
    }
}