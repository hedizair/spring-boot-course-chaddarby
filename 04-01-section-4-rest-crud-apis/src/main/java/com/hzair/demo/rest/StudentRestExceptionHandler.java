package com.hzair.demo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// * This class will handle exceptions for the StudentRestController automatically, nothing more to do oO
// * @ControllerAdvice allow to execute code for all the controllers in the project. It can be ExceptionHandler, or also @InitBinder, or @ModelAttribute. 
@ControllerAdvice
public class StudentRestExceptionHandler {

    @ExceptionHandler        
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) { // * Handle StudentNotFoundException exception.
        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); 
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc) { // * Handle all exception except StudentNotFoundException.
        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST); 
    }
}
