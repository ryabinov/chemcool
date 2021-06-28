package com.chemcool.school.lesson.domain.matches;

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
public class ChemMatchingTask {

    @Id
    @Column(unique=true, name = "task_id")
    private String taskId;

    @Column(length = 10000)
    private String description;
    private Integer chapterId;
    private Integer referenceId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id")
    private List<CoupleForMatching> coupleForMatchingList;

    public static ChemMatchingTask createChemistryMatchingTask(ChemMatchingTaskExample chemMatchingTaskExample) {
        return new ChemMatchingTask(
                UUID.randomUUID().toString(),
                chemMatchingTaskExample.getDescription(),
                chemMatchingTaskExample.getChapterId(),
                chemMatchingTaskExample.getReferenceId(),
                chemMatchingTaskExample.getCoupleForMatchingList()
        );
    }

}