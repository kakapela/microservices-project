package com.kapelczakservices.customer.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(EmailAlreadyTakenException.class)
    public ResponseEntity<String> emailAlreadyTakenException(EmailAlreadyTakenException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
