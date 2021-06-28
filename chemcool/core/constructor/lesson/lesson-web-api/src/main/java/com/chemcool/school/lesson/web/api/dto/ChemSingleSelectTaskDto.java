package com.chemcool.school.lesson.web.api.dto;


import com.chemcool.school.lesson.domain.singleselect.ChemSingleSelectTask;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChemSingleSelectTaskDto {
    private String taskDtoId;
    private String description;
    private String correctAnswer;
    private int chapterId;
    private int referenceId;
    private String incorrectAnswerOne;
    private String incorrectAnswerTwo;
    private String incorrectAnswerThree;
    private String incorrectAnswerFour;
    private String taskType;

    public ChemSingleSelectTaskDto(ChemSingleSelectTask task) {
        this.taskDtoId = task.getTaskId();
        this.description = task.getDescription();
        this.correctAnswer = task.getCorrectAnswer();
        this.chapterId = task.getChapterId();
        this.referenceId = task.getReferenceId();
        this.incorrectAnswerOne = task.getIncorrectAnswerOne();
        this.incorrectAnswerTwo = task.getIncorrectAnswerTwo();
        this.incorrectAnswerThree = task.getIncorrectAnswerThree();
        this.incorrectAnswerFour = task.getIncorrectAnswerFour();
        this.taskType = "SingleSelect";
    }
}
