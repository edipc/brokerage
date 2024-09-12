package com.example.brokerage.exception;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class BaseException extends RuntimeException{

    private final String field;
    private final int code;
    private final String message;

    protected BaseException(String field, int errorCode, String message) {
        super(message);
        this.code = errorCode;
        this.field = field;
        this.message = message;
    }
}
