package com.chemcool.school.answers.domain.singleselect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChemSingleSelectTask {
    private String taskId;
    private String description;
    private String correctAnswer;
    private int chapterId;
    private int referenceId;
    private String incorrectAnswerOne;
    private String incorrectAnswerTwo;
    private String incorrectAnswerThree;
    private String incorrectAnswerFour;
}
