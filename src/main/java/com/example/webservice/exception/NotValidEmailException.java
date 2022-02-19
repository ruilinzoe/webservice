package com.example.webservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotValidEmailException extends RuntimeException{
    public String sayHello() {
        return String.format("400");
    }
}
