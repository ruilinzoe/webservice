package com.example.webservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class CustomErrorHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<CustomError> handleConstraintViolationException(SQLIntegrityConstraintViolationException exception,
                                                                          ServletWebRequest webRequest) throws IOException {
//        webRequest.getResponse().sendError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());

        CustomError customError = new CustomError();
        customError.setStatus(HttpStatus.BAD_REQUEST);
        customError.setMessage(exception.getMessage());
//        customError.addConstraintErrors(exception.getMessage());
        return ResponseEntity.badRequest().body(customError);
    }
}
