package com.furkanbilgin.week3.springmvc.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(BookNotFoundException e) {
        return ResponseEntity.status(404).body("Book with ID " + e.getBookId() + " not found.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception e) {
        return ResponseEntity.status(500).body("An unexpected error occurred: " + e.getMessage());
    }
}
