package com.development.commons.shared.exceptions;

import org.springframework.http.HttpStatus;

public abstract class DomainException extends RuntimeException {
    protected static final HttpStatus CONFLICT = HttpStatus.CONFLICT;

    public final HttpStatus httpStatus;
    public final Integer errorCode;

    protected DomainException(HttpStatus httpStatus, Integer errorCode, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }
}
