package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<String> responseStatusExceptionHandler(ResponseStatusException ex){
        return new ResponseEntity<>(ex.getReason(), ex.getStatusCode());
    }
}
