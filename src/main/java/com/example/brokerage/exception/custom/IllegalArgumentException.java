package com.example.brokerage.exception.custom;

import com.example.brokerage.exception.BaseException;

public class IllegalArgumentException extends BaseException {

    public IllegalArgumentException(int errorCode, String errorMessage, String errorDescription) {
        super(errorDescription, errorCode, errorMessage);
    }
}
