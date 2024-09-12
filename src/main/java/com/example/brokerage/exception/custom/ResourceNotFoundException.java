package com.example.brokerage.exception.custom;

import com.example.brokerage.exception.BaseException;

public class ResourceNotFoundException extends BaseException {

    public ResourceNotFoundException(int errorCode, String errorMessage, String errorDescription) {
        super(errorDescription, errorCode, errorMessage);
    }
}
