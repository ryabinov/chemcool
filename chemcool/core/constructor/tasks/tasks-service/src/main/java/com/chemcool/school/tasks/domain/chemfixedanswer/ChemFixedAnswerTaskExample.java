package com.chemcool.school.tasks.domain.chemfixedanswer;

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
