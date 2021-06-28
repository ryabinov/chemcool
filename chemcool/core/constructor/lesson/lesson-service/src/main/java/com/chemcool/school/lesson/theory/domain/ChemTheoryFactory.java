package com.chemcool.school.lesson.theory.domain;


import com.chemcool.school.lesson.theory.exception.ChemTheoryFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ChemTheoryFactory {

    public static ChemTheory createTheory(ChemTheoryExample example) {
        if (example.getTheoryExampleChapter() < 1) {
            throw new ChemTheoryFormatException("Глава урока не может быть меньше 1.");
        }
        if (example.getTheoryExampleReferences() < 1 || example.getTheoryExampleReferences() > 4) {
            throw new ChemTheoryFormatException("Уровень химии не может быть меньше 1 или больше 4.");
        }
        log.info("Попытка создать урок: " + "[" + example.getTheoryExampleName() + "]");
        return ChemTheory.createChemistryTheory(example);
    }

}
