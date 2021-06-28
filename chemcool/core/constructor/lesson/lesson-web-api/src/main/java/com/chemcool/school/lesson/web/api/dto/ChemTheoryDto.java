package com.chemcool.school.lesson.web.api.dto;

import com.chemcool.school.lesson.theory.domain.ChemTheory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChemTheoryDto {
    private String theoryDtoId;
    private String theoryName;
    private String theoryDescription;
    private Integer theoryChapter;
    private Integer theoryReferences;
    private String taskType;

    public ChemTheoryDto(ChemTheory theory) {
        this.theoryDtoId=theory.getTheoryId();
        this.theoryName = theory.getTheoryName();
        this.theoryDescription = theory.getTheoryDescription();
        this.theoryChapter = theory.getTheoryChapter();
        this.theoryReferences = theory.getTheoryReferences();
        this.taskType = "Theory";
    }
}
