package com.codewithaashu.blog.blogging_website.exceptions;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.codewithaashu.blog.blogging_website.Response.ErrorResponse;

//only exception is throw. so, controller give an error. we want that if exception occurs, controller handle it and send error message as response 

//to create this class as Global Exception handle we use RestControllerAdvice annotations
@RestControllerAdvice
public class GlobalExceptionHandler {

    // specify which exception is handle by below method by ExceptonHandler
    // Annotations
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException exception) {
        // get the error message
        String errorMessage = exception.getMessage();
        // send error response as http Response
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(errorMessage, false), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleIntegrityConstraintVoilationException(
            SQLIntegrityConstraintViolationException exception) {
        String message = exception.getMessage();
        String errorMessage = String.format("%s for value : '%s' in your table", message.split("'")[0],
                message.split("'")[1]);
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(errorMessage, false), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HashMap<String, String>> handleValidationException(
            MethodArgumentNotValidException exception) {
        // create a hashmap to mapping the error. it returns field and error message in
        // that field.
        HashMap<String, String> errorMap = new HashMap<>();
        // it return list of objet errors
        exception.getBindingResult().getAllErrors().forEach(error -> {
            // in every object there is two thing i.e. field and message
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errorMap.put(field, message);
        });
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }
}
