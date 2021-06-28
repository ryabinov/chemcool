package com.chemcool.school.tasks.dto.chemmatches;


import com.chemcool.school.tasks.domain.chemmatches.ChemistryMatchingTask;
import com.chemcool.school.tasks.domain.chemmatches.CoupleForMatching;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChemistryMatchingTaskDto {
    private String taskId;
    private String description;
    private Integer chapterId;
    private Integer referenceId;
    private List<CoupleForMatching> coupleForMatchingList;

    public ChemistryMatchingTaskDto(ChemistryMatchingTask task) {
        this.taskId = task.getTaskId();
        this.description = task.getDescription();
        this.chapterId = task.getChapterId();
        this.referenceId = task.getReferenceId();
        this.coupleForMatchingList = task.getCoupleForMatchingList();
    }
}
