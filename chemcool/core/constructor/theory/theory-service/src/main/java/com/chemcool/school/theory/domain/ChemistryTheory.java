package com.chemcool.school.theory.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chemistry_theory_page")
public class ChemistryTheory {
    @Id
    private String theoryId;

    @Column(name = "theory_name")
    @NonNull
    private String theoryName;

    @Column(
            name = "theory_description",
            length = 10000
    )
    private String theoryDescription;

    @NonNull
    @Column(name = "theory_chapter")
    private Integer theoryChapter;

    @NonNull
    @Column(name = "theory_references")
    private Integer theoryReferences;



    public static ChemistryTheory createChemistryTheory(
            ChemistryTheoryExample example
    ) {
        return new ChemistryTheory(
                UUID.randomUUID().toString(),
                example.getTheoryExampleName(),
                example.getTheoryExampleDescription(),
                example.getTheoryExampleChapter(),
                example.getTheoryExampleReferences()
        );
    }

}