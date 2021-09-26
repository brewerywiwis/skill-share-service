package com.example.accountservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class RestExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public void badRequestHandle() {
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public void exceptionHandle() {
    }
}