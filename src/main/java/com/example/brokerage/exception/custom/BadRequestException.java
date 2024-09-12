package com.example.brokerage.exception.custom;

import com.example.brokerage.exception.BaseException;

public class BadRequestException extends BaseException {

    public BadRequestException(int errorCode, String errorMessage, String errorDescription) {
        super(errorDescription, errorCode, errorMessage);
    }
}
