package com.chemcool.school.tasks.domain.chemmatches;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "couples_for_matching")
public class CoupleForMatching {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coupleId;

    @Column(name = "left_couple")
    private String leftCouple;

    @Column(name = "right_couple")
    private String rightCouple;

    public CoupleForMatching(String leftCouple, String rightCouple) {
        this.leftCouple = leftCouple;
        this.rightCouple = rightCouple;
    }
}