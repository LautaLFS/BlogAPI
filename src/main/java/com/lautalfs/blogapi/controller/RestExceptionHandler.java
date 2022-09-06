package com.lautalfs.blogapi.controller;

import com.lautalfs.blogapi.dto.ApiResponse;
import com.lautalfs.blogapi.exception.ResourceNotFoundException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<Object> resourceNotFoundExceptionHandler(ResourceNotFoundException ex, WebRequest request){
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message, false);
        return handleExceptionInternal(ex, apiResponse,new HttpHeaders(),HttpStatus.NOT_FOUND,request);
    }
    @ExceptionHandler(value = PropertyReferenceException.class)
    public ResponseEntity<Object> propertyNotFoundExceptionHandler(PropertyReferenceException ex, WebRequest request){
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message, false);
        return handleExceptionInternal(ex, apiResponse,new HttpHeaders(),HttpStatus.NOT_ACCEPTABLE,request);
    }
    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<Object> badCredentialsExceptionHandler(BadCredentialsException ex, WebRequest request){
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message, false);
        return handleExceptionInternal(ex, apiResponse,new HttpHeaders(),HttpStatus.BAD_REQUEST,request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> response = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error->{
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            response.put(fieldName, message);
        });
        return handleExceptionInternal(ex, response ,new HttpHeaders(),HttpStatus.NOT_FOUND,request);
    }

}

