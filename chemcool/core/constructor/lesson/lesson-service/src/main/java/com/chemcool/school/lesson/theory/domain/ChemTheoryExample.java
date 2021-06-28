package com.chemcool.school.lesson.theory.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChemTheoryExample {
    private String theoryExampleName;
    private String theoryExampleDescription;
    private Integer theoryExampleChapter;
    private Integer theoryExampleReferences;

    public static ChemTheoryExample fromTheoryExample(
            String name,
            String description,
            Integer chapter,
            Integer references
    ) {
        return new ChemTheoryExample(
                name,
                description,
                chapter,
                references
        );
    }

}
