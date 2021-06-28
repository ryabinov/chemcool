package com.chemcool.school.answers.domain.fixedanswer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChemFixedAnswerTask {
    private String taskId;
    private String description;
    private String rightAnswer;
    private int chapterId;
    private int referenceId;
}
