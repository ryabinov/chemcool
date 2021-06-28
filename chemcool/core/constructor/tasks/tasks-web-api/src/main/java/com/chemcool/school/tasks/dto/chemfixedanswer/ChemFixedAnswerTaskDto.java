package com.chemcool.school.tasks.dto.chemfixedanswer;

import com.chemcool.school.tasks.domain.chemfixedanswer.ChemFixedAnswerTask;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChemFixedAnswerTaskDto {
    private String taskId;
    private String description;
    private String rightAnswer;
    private int chapterId;
    private int referenceId;

    public ChemFixedAnswerTaskDto(ChemFixedAnswerTask task) {
        this.taskId = task.getTaskId();
        this.description = task.getDescription();
        this.rightAnswer = task.getRightAnswer();
        this.chapterId = task.getChapterId();
        this.referenceId = task.getReferenceId();
    }
}
