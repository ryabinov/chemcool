package com.chemcool.school.lesson.domain.singleselect;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Класс вопрос с одним выбором ответа.
 * Один правильный ответ, и до 4х неправильных. *
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chemistry_single_select_task")
public class ChemSingleSelectTask {
    @Id
    @Column(name = "id", unique=true)
    private String taskId;

    @Column(name = "description", length = 10_000)
    private String description;

    @Column(name = "correct_answer")
    private String correctAnswer;

    @Column(name = "chapter_id")
    private int chapterId;

    @Column(name = "reference_id")
    private int referenceId;

    @Column(name = "incorrect_answer_one")
    private String incorrectAnswerOne;
    @Column(name = "incorrect_answer_two")
    private String incorrectAnswerTwo;
    @Column(name = "incorrect_answer_three")
    private String incorrectAnswerThree;
    @Column(name = "incorrect_answer_four")
    private String incorrectAnswerFour;

    public static ChemSingleSelectTask createChemistrySingleSelectTask(
            ChemSingleSelectTaskExample example
    ) {
        return new ChemSingleSelectTask(
                UUID.randomUUID().toString(),
                example.getTaskExampleDescription(),
                example.getTaskExampleCorrectAnswer(),
                example.getTaskExampleChapterId(),
                example.getTaskExampleReferenceId(),
                example.getTaskExampleIncorrectAnswerOne(),
                example.getTaskExampleIncorrectAnswerTwo(),
                example.getTaskExampleIncorrectAnswerThree(),
                example.getTaskExampleIncorrectAnswerFour()
        );
    }
}
