package com.chemcool.school.answers.domain.matches;

import com.chemcool.school.answers.domain.matches.ChemistryMatchingTask;
import com.chemcool.school.answers.domain.matches.CoupleForMatching;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "chem_mathes_correct_answers")
public class ChemmathesCorrectAnswers {
    @Id
    @Column(name = "task_id")
    String id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id")
    private List<CoupleForMatching> correctCoupleForMatchingList;

    public static ChemmathesCorrectAnswers createChemmathesCorrectAnswers(ChemistryMatchingTask task) {
        return new ChemmathesCorrectAnswers(task.getTaskId(), task.getCoupleForMatchingList());
    }
}
