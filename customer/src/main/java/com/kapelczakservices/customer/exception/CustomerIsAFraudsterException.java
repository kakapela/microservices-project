package com.kapelczakservices.customer.exception;

public class CustomerIsAFraudsterException extends RuntimeException {
    public CustomerIsAFraudsterException(String message) {
        super(message);
    }
}
