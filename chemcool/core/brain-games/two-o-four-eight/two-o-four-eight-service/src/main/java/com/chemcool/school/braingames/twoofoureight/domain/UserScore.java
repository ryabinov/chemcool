package com.chemcool.school.braingames.twoofoureight.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_and_score")
@Data
@NoArgsConstructor
public class UserScore {

    public UserScore(Long id, String userId, String score) {
        this.id = id;
        this.userId = userId;
        this.score = score;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "score")
    private String score;

}