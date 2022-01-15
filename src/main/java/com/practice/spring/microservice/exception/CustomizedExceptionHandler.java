package com.practice.spring.microservice.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * This method handles all the generic exceptions that occur at server side and were missed to handle
     * @param ex : Any exception
     * @param request : request with exception
     * @return ResponseEntity with exception response and http status
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * This method handles custom UserNotFoundException that occur at server side while handling a REST request.
     * @param ex : UserNotFoundException while retrieving a user
     * @param request : request with exception
     * @return ResponseEntity with exception response and http status
     */
    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder errorStringBuilder = new StringBuilder();
        bindingResult.getAllErrors().forEach(objectError -> errorStringBuilder.append(objectError.getDefaultMessage()).append(", "));
        errorStringBuilder.replace(errorStringBuilder.lastIndexOf(", "), errorStringBuilder.length(), "");
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Failed", errorStringBuilder.toString());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
