package com.example.E_commerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllException(Exception ex){
        String error="Exception:"+ ex.getClass().getSimpleName()+"\n"
                +"Message:"+ ex.getMessage()+"\n"
                +"Cause:"+ ex.getCause();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex){

        String error="Exception:"+ ex.getClass().getSimpleName()+"\n"
                +"Message:"+ ex.getMessage()+"\n"
                +"Cause:"+ ex.getCause();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> inputValidationException(MethodArgumentNotValidException ex){
       List<String> errorList= ex.getBindingResult().getFieldErrors().stream().map((error)-> {
                   return error.getField() + ":" + error.getDefaultMessage();
               }
                ).collect(Collectors.toList());
       return ResponseEntity.badRequest().body(errorList);
    }
}
