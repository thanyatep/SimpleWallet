package com.example.demo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

  private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> processUnhandledException(
      Exception ex, WebRequest webRequest) {

    logger.error(ex.getMessage(), ex);
    ErrorResponse response = new ErrorResponse();
    response.setResultCode(500);
    response.setReason("System Error");
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }


  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<ErrorResponse> processMissingServletRequestParameterException(
      MissingServletRequestParameterException ex) {
    ErrorResponse response = new ErrorResponse();
    response.setResultCode(400);
    String errors =" Invalid Parameter Name " + ex.getParameterName();
    response.setReason(errors);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }


  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException e) {
    ErrorResponse response = new ErrorResponse();

    String errors =
        e.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(
                error ->
                        "Invalid Parameter Name "
                        + error.getField())
            .collect(Collectors.joining(", "));
    if (StringUtils.isEmpty(errors)) {
      errors = e.getBindingResult().getGlobalError().getDefaultMessage();
    }
    response.setResultCode(400);
    response.setReason(errors);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ExceptionHandler(ValidRequestException.class)
  public ResponseEntity<ErrorResponse> handleValidRequestException(
          ValidRequestException e) {
    ErrorResponse response = new ErrorResponse();
    response.setResultCode(400);
    response.setReason(e.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

}
