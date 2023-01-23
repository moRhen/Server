package org.example.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {PostNotFoundException.class})
  protected ResponseEntity<Object> handleException(PostNotFoundException ex, WebRequest request) {
    return handleExceptionInternal(
        ex, "Post not found in db, and external service", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }
}
