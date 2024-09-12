package com.example.brokerage.exception.handler;

import com.example.brokerage.controller.resource.ErrorResource;
import com.example.brokerage.exception.ApiMessage;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.example.brokerage.exception.custom.*;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorResource handleException(ResourceNotFoundException ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getField());
        return ErrorResource.builder().status(ex.getCode())
                .message(ex.getMessage()).errors(errors).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ErrorResource handleException(BadRequestException ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getField());
        return ErrorResource.builder().status(ex.getCode())
                .message(ex.getMessage()).errors(errors).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResource handleException(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>(ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList());

        return ErrorResource.builder()
                .status(ApiMessage.FIELD_VALIDATION.getStatus())
                .message(ApiMessage.FIELD_VALIDATION.getMessage())
                .errors(errors)
                .build();

    }
}