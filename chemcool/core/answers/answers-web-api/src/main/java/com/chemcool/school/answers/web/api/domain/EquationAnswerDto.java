package com.chemcool.school.answers.web.api.domain;

public class EquationAnswerDto extends AnswerDto{
    boolean[] resultSet;

    public EquationAnswerDto() {
    }

    public EquationAnswerDto(boolean[] resultSet) {
        this.resultSet = resultSet;
    }

    public boolean[] getResultSet() {
        return resultSet;
    }

    public void setResultSet(boolean[] resultSet) {
        this.resultSet = resultSet;
    }
}
