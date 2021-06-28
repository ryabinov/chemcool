package com.chemcool.school.lesson.domain.equation;

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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chemistry_equations")
public class ChemEquationsTask {

    @Id
    @Column(name = "task_id")
    private String taskId;

    @Column(name = "description", length = 10000)
    private String description;

    @Column(name = "right_answer")
    @NonNull
    private String rightAnswer;

    @Column(name = "chapter_id")
    @NonNull
    private int chapterId;

    @Column(name = "reference_id")
    @NonNull
    private int referenceId;

    public static ChemEquationsTask createChemEquationsTask(
            ChemEquationsTaskExample task
    ){
        return new ChemEquationsTask(
                UUID.randomUUID().toString(),
                task.getDescription(),
                task.getRightAnswer(),
                task.getChapterId(),
                task.getReferenceId()
        );
    }
}