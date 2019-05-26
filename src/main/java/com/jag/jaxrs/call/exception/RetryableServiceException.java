package com.jag.jaxrs.call.exception;

public class RetryableServiceException extends RuntimeException {
    public RetryableServiceException(String message) {
        super(message);
    }
}
