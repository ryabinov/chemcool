package com.chemcool.school.tasks.domain.chemmatches;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChemistryMatchingTaskExample {
    private String chemistryMatchingTaskUuid;
    private String description;
    private Integer chapterId;
    private Integer referenceId;
    private List<CoupleForMatching> coupleForMatchingList;
}
