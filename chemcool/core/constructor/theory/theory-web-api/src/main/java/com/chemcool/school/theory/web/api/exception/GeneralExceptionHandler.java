package com.chemcool.school.theory.web.api.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralExceptionHandler {

    private String message;

    public GeneralExceptionHandler(Exception exception) {
        this.message = exception.getMessage();
    }

}
