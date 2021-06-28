package com.chemcool.school.answers.domain.matches;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChemistryMatchingTask {
    private String taskId;
    private String description;
    private Integer chapterId;
    private Integer referenceId;
    private List<CoupleForMatching> coupleForMatchingList;
}