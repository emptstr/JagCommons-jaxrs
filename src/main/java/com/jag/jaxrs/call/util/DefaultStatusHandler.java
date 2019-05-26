package com.jag.jaxrs.call.util;

import javax.ws.rs.core.Response.Status.Family;

import com.jag.jaxrs.call.exception.NonRetryableServiceException;
import com.jag.jaxrs.call.exception.RetryableServiceException;

public class DefaultStatusHandler implements StatusHandler {
    @Override
    public void handle(int statusCode) {
        switch (Family.familyOf(statusCode)) {
            case SERVER_ERROR:
                throw new RetryableServiceException("Failed with status: " + statusCode);
            case REDIRECTION:
            case CLIENT_ERROR:
            case OTHER:
                throw new NonRetryableServiceException("Failed with status: " + statusCode);
        }
    }
}
