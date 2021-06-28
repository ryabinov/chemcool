package com.chemcool.school.lesson.web.api.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiWebRestControllerExceptionHandler {

    @ExceptionHandler
    private ResponseEntity<GeneralExceptionHandler> generalExceptionHandler(Exception exception) {
        return new ResponseEntity<>(new GeneralExceptionHandler(exception), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ChemEquationsAnswerException> ChemEquationsAnswerHandler(ChemEquationsAnswerException exception) {
        return new ResponseEntity<>(new ChemEquationsAnswerException(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
