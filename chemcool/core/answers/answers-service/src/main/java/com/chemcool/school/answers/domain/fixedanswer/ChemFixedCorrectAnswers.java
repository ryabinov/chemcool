package com.chemcool.school.answers.domain.fixedanswer;

import com.chemcool.school.answers.domain.AbstractBaseCorrectAnswersClass;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@Table(name = "chemfixed_correct_answers")
public class ChemFixedCorrectAnswers extends AbstractBaseCorrectAnswersClass {

    public ChemFixedCorrectAnswers(String taskId, String rightAnswer) {
        super(taskId, rightAnswer);
    }

    public static ChemFixedCorrectAnswers createChemFixedCorrectAnswers(ChemFixedAnswerTask task) {
        return new ChemFixedCorrectAnswers(task.getTaskId(), task.getRightAnswer());
    }
}
