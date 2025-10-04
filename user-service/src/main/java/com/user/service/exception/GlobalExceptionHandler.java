package com.user.service.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistException(UserAlreadyExistsException ex) {
        return new ResponseEntity<>(ErrorResponse.builder().message(ex.getMessage()).errorCode("AUTHN-001").build(), HttpStatus.FOUND);
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoDataFoundException(NoDataFoundException ex) {
        return new ResponseEntity<>(ErrorResponse.builder().message(ex.getMessage()).errorCode("AUTHN-002").build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleInputValidationException(MethodArgumentNotValidException ex){
        List<FieldError> listErrors=ex.getFieldErrors();
        Map<String,String> errors=new HashMap<>();
        listErrors.forEach(fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
        String message=errors.entrySet().stream().map(entry-> entry.getKey() + ": "+ entry.getValue()).collect(Collectors.joining("; "));
        return new ResponseEntity<>(ErrorResponse.builder().message(message).errorCode("AUTHN-003").build(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex){
        return new ResponseEntity<>(ErrorResponse.builder().message("Please enter only a number value for id and a text value for name and email fields.").errorCode("AUTHN-004").build(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex){
        return new ResponseEntity<>(ErrorResponse.builder().message("You made an invalid request. Please check your URL again!").errorCode("AUTHN-005").build(),HttpStatus.BAD_REQUEST);
    }


}
