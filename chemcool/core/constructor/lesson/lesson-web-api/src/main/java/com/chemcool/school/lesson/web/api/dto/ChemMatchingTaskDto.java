package com.chemcool.school.lesson.web.api.dto;

import com.chemcool.school.lesson.domain.matches.ChemMatchingTask;
import com.chemcool.school.lesson.domain.matches.CoupleForMatching;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChemMatchingTaskDto {
    private String taskId;
    private String description;
    private Integer chapterId;
    private Integer referenceId;
    private List<CoupleForMatching> coupleForMatchingList;
    private String taskType;

    public ChemMatchingTaskDto(ChemMatchingTask task) {
        this.taskId = task.getTaskId();
        this.description = task.getDescription();
        this.chapterId = task.getChapterId();
        this.referenceId = task.getReferenceId();
        this.coupleForMatchingList = task.getCoupleForMatchingList();
        this.taskType = "Matches";
    }
}
