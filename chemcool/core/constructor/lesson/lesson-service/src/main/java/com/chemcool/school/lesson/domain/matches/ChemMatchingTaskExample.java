package com.chemcool.school.lesson.domain.matches;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChemMatchingTaskExample {
    private String chemistryMatchingTaskUuid;
    private String description;
    private Integer chapterId;
    private Integer referenceId;
    private List<CoupleForMatching> coupleForMatchingList;
}
