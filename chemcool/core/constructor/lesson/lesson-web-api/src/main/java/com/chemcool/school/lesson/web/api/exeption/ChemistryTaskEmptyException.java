package com.chemcool.school.lesson.web.api.exeption;

public class ChemistryTaskEmptyException extends RuntimeException{
    public ChemistryTaskEmptyException(String message) {
        super(message);
    }
}
