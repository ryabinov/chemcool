package com.chemcool.school.theory.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChemistryTheoryExample {
    private String theoryExampleName;
    private String theoryExampleDescription;
    private Integer theoryExampleChapter;
    private Integer theoryExampleReferences;

    public static ChemistryTheoryExample fromTheoryExample(
            String name,
            String description,
            Integer chapter,
            Integer references
    ) {
        return new ChemistryTheoryExample(
                name,
                description,
                chapter,
                references
        );
    }
}
