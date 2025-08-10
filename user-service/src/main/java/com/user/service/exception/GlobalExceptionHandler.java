package com.user.service.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoDataFoundException(NoDataFoundException ex) {
        return new ResponseEntity<>(ErrorResponse.builder().message(ex.getMessage()).errorCode("AUTHN-002").build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GenericValidationException.class)
    public ResponseEntity<ErrorResponse> handleGenericValidationException(GenericValidationException ex) {
        return new ResponseEntity<>(ErrorResponse.builder().message(ex.getMessage()).errorCode("AUTHN-001").build(), HttpStatus.BAD_REQUEST);
    }
}
