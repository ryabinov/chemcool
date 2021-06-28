package com.chemcool.school.tasks.domain.chemsingleselect;

import com.chemcool.school.tasks.ChemSingleSelectTaskFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ChemSingleSelectTaskFactory {

    public static ChemSingleSelectTask createTask(ChemSingleSelectTaskExample example){

        if (example.getTaskExampleChapterId() < 1 || example.getTaskExampleChapterId() > 4) {
            throw new ChemSingleSelectTaskFormatException("Уровень химии не может быть меньше 1 или больше 4.");
        }
        log.info("Попытка создать задачу с ВЫБОРОМ ОДНОГО ОТВЕТА ИЗ МНОЖЕСТВА");
        return ChemSingleSelectTask.createChemistrySingleSelectTask(example);
    }
}
