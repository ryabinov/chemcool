package com.chemcool.school.tasks.dto.chemsingleselect;



import com.chemcool.school.tasks.domain.chemsingleselect.ChemSingleSelectTask;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChemistrySingleSelectTaskDto {
    private String taskDtoId;
    private String description;
    private String correctAnswer;
    private int chapterId;
    private int referenceId;
    private String incorrectAnswerOne;
    private String incorrectAnswerTwo;
    private String incorrectAnswerThree;
    private String incorrectAnswerFour;

    public ChemistrySingleSelectTaskDto(ChemSingleSelectTask task) {
        this.taskDtoId = task.getTaskId();
        this.description = task.getDescription();
        this.correctAnswer = task.getCorrectAnswer();
        this.chapterId = task.getChapterId();
        this.referenceId = task.getReferenceId();
        this.incorrectAnswerOne = task.getIncorrectAnswerOne();
        this.incorrectAnswerTwo = task.getIncorrectAnswerTwo();
        this.incorrectAnswerThree = task.getIncorrectAnswerThree();
        this.incorrectAnswerFour = task.getIncorrectAnswerFour();
    }
}
