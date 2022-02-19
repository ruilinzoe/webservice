package com.example.webservice.exception;

import org.springframework.http.HttpStatus;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class CustomError {
    public void setStatus(HttpStatus badRequest) {

    }

    public void setMessage(String message) {

    }

    public void addConstraintErrors(Set<ConstraintViolation<?>> constraintViolations) {

    }
}
