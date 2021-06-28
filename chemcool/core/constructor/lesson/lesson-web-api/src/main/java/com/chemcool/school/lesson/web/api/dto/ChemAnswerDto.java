package com.chemcool.school.lesson.web.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChemAnswerDto {
    private boolean answerResult;
    private String[] testResult;
    private int score;
}
