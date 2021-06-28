package com.chemcool.school.answers.web.api.domain;

public abstract class AnswerDto {
    int score;
    boolean result;

    public AnswerDto() {
        this.score = 0;
        this.result = false;
    }

    public void addScore(int i){
        this.score +=i;
    }

    public void subScore(int i){
        this.score -=i;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
