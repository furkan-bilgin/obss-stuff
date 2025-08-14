package com.furkanbilgin.finalproject.movieportal.exception;

import io.jsonwebtoken.JwtException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

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
  public ResponseEntity<Map<String, String>> handleAllExceptions(Exception ex) {
    var status = HttpStatus.INTERNAL_SERVER_ERROR;
    var responseStatusAnnotation = ex.getClass().getAnnotation(ResponseStatus.class);
    if (responseStatusAnnotation != null) {
      status = responseStatusAnnotation.value();
    } else if (ex instanceof InvalidCredentialsException
        || ex instanceof AuthorizationDeniedException
        || ex instanceof UsernameNotFoundException) {
      status = HttpStatus.UNAUTHORIZED;
    } else if (ex instanceof UserNotFoundException) {
      status = HttpStatus.NOT_FOUND;
    } else if (ex instanceof UserAlreadyExistsException) {
      status = HttpStatus.CONFLICT;
    } else if (ex instanceof JwtException) {
      status = HttpStatus.UNAUTHORIZED;
    }

    var error = new HashMap<String, String>();
    error.put("error", ex.getMessage());
    logger.error("Exception occurred: ", ex);
    return new ResponseEntity<>(error, status);
  }
}
