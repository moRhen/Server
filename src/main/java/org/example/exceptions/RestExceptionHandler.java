package org.example.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {PostNotFoundException.class})
  protected ResponseEntity<Object> handleException(PostNotFoundException ex, WebRequest request) {
    return handleExceptionInternal(
        ex, "Post not found in db, and external service", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  @ExceptionHandler(value = {ValidationException.class})
  protected ResponseEntity<Object> handleException(ValidationException ex) {
    log.error("Invalid request {}", ex.getMessage());
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = RuntimeException.class)
  protected void handleException(RuntimeException exception) {
    log.error("Runtime", exception);
  }
}
