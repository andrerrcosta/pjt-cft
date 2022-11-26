package com.nobblecrafts.challenge.cft.exception;

public class CftNotFoundException extends DomainException {

    public CftNotFoundException(String message) {
        super(message);
    }

    public CftNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
