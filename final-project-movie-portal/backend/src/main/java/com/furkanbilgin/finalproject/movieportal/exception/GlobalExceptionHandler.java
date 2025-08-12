package com.furkanbilgin.finalproject.movieportal.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
    var errors = new HashMap<String, String>();
    ex.getBindingResult()
        .getFieldErrors()
        .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
    return errors;
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, String>> handleGeneralExceptions(Exception ex) {
    var error = new HashMap<String, String>();
    error.put("error", ex.getMessage());
    var status = HttpStatus.INTERNAL_SERVER_ERROR;

    var responseStatusAnnotation = ex.getClass().getAnnotation(ResponseStatus.class);
    if (responseStatusAnnotation != null) {
      status = responseStatusAnnotation.value();
    }
    return new ResponseEntity<>(error, status);
  }
}
