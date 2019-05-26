package com.jag.jaxrs.call.exception;

public class NonRetryableServiceException extends RuntimeException {
    public NonRetryableServiceException(String message) {
        super(message);
    }
}
