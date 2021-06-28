package com.chemcool.school.answers.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AbstractBaseCorrectAnswersClass {

    @Id
    @Column(name = "task_id")
    String id;

    @Column(name = "correct_answer")
    String correctAnswer;
}
