package com.chemcool.school.lesson.web.api.exeption;

public class ChemTaskEmptyException extends RuntimeException {
    public ChemTaskEmptyException(String message) {
        super(message);
    }
}
