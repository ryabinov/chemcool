package com.chemcool.school.lesson.web.api.dto;

import com.chemcool.school.lesson.domain.fixedanswer.ChemFixedAnswerTask;
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
    private String taskType;

    public ChemFixedAnswerTaskDto(ChemFixedAnswerTask task) {
        this.taskId = task.getTaskId();
        this.description = task.getDescription();
        this.rightAnswer = task.getRightAnswer();
        this.chapterId = task.getChapterId();
        this.referenceId = task.getReferenceId();
        this.taskType = "FixedAnswer";
    }
}
