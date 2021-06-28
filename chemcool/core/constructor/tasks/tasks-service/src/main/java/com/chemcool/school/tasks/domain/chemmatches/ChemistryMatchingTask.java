package com.chemcool.school.tasks.domain.chemmatches;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chemistry_matching_task")
public class ChemistryMatchingTask {

    @Id
    @Column(unique=true, name = "task_id")
    private String taskId;

    @Column(length = 10000, name = "description")
    private String description;

    @Column(name = "chapter_id")
    private Integer chapterId;

    @Column(name = "reference_id")
    private Integer referenceId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id")
    private List<CoupleForMatching> coupleForMatchingList;

    public static ChemistryMatchingTask createChemistryMatchingTask(ChemistryMatchingTaskExample chemistryMatchingTaskExample) {
        return new ChemistryMatchingTask(
                UUID.randomUUID().toString(),
                chemistryMatchingTaskExample.getDescription(),
                chemistryMatchingTaskExample.getChapterId(),
                chemistryMatchingTaskExample.getReferenceId(),
                chemistryMatchingTaskExample.getCoupleForMatchingList()
        );
    }

}