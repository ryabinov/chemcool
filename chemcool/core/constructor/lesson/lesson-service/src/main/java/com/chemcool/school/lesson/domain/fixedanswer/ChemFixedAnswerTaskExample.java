package com.chemcool.school.lesson.domain.fixedanswer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChemFixedAnswerTaskExample {

    private String description;
    private String rightAnswer;
    private int chapterId;
    private int referenceId;
}
