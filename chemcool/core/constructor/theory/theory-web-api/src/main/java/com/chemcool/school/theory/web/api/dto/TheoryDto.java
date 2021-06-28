package com.chemcool.school.theory.web.api.dto;

import com.chemcool.school.theory.domain.ChemistryTheory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheoryDto {
    private String theoryDtoId;
    private String theoryName;
    private String theoryDescription;
    private Integer theoryChapter;
    private Integer theoryReferences;

    public TheoryDto(ChemistryTheory theory) {
        this.theoryDtoId=theory.getTheoryId();
        this.theoryName = theory.getTheoryName();
        this.theoryDescription = theory.getTheoryDescription();
        this.theoryChapter = theory.getTheoryChapter();
        this.theoryReferences = theory.getTheoryReferences();
    }
}
