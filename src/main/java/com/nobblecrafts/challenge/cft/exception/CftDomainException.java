package com.nobblecrafts.challenge.cft.exception;

public class CftDomainException extends DomainException {

    public CftDomainException(String message) {
        super(message);
    }

    public CftDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
