package com.example.brokerage.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor
public enum ApiMessage {

    ASSET_NOT_MATCH( 2051,"Asset not found with given information"),
    INVALID_IBAN( 2052,"Given IBAN is not valid"),
    ASSET_NOT_FOUND( 2053,"Insufficient funds or TRY asset not found"),
    INSUFFICIENT_FUNDS( 2054,"Insufficient funds"),
    PENDING_ORDER_CANCELED(2055, "Only pending orders can be canceled"),
    ORDER_SIZE_NOT_MATCH(2056, "Order sizes or prices do not match"),
    PENDING_ORDER_MATCH(2057, "Only pending orders can be matched"),
    CUSTOMER_NOT_FOUND(2058, "Customer not found with given id"),
    CUSTOMER_ALREADY_EXISTS(2059, "Customer already exists with given id"),

    FIELD_VALIDATION(4095,"Field validation error.");

    private final int status;
    private final String message;
}
