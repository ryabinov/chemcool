package com.chemcool.school.tasks.domain.chemfixedanswer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ChemFixedAnswerTaskFactory {
    public static ChemFixedAnswerTask createChemistryFixedAnswerTask(ChemFixedAnswerTaskExample example){

        if (example.getChapterId() < 1 || example.getChapterId() > 4) {
            throw new ChemFixedAnswerTaskException("Ошибка! Поле \"Глава\" имеет значение от 1 до 4 ");
        }
        if (example.getReferenceId() < 1 || example.getReferenceId() > 4) {
            throw new ChemFixedAnswerTaskException("Ошибка! Поле \"Раздел\" имеет значение от 1 до 4 ");
        }
        log.info("Попытка создать урок.");
        return ChemFixedAnswerTask.createChemistryFixedAnswerTask(example);
    }
}
