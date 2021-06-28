package com.chemcool.school.answers.web.api.domain;

public enum TaskType {
    FIXED_ANSWER("fixed_answer"),
    SINGLE_SELECT("fixed_answer"),
    EQUATION("fixed_answer"),
    MATCHES("fixed_answer");

    private String task;

    TaskType(String task) {
        this.task = task;
    }
}
