package com.testserver.test.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler({Exception.class})
    public ResponseEntity notValid(Exception exception){
        exception.printStackTrace();
        System.out.println(exception.getMessage());
        return new ResponseEntity("server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
