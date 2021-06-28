package com.chemcool.school.lesson.web.api.dto;

import com.chemcool.school.lesson.domain.equation.ChemEquationsTask;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChemEquationsTaskDto {
    private String taskId;
    private String description;
    private int chapterId;
    private int referenceId;
    private String taskType;

    public ChemEquationsTaskDto(ChemEquationsTask task) {
        this.taskId = task.getTaskId();
        this.description = task.getDescription();
        this.chapterId = task.getChapterId();
        this.referenceId = task.getReferenceId();
        this.taskType = "Equations";
    }
}
