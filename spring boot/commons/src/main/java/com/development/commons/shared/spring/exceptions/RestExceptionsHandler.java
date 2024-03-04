package com.development.commons.shared.spring.exceptions;


import com.development.commons.shared.exceptions.DomainException;
import com.development.commons.shared.spring.message.CustomMessageSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.server.ServerWebInputException;

@RestControllerAdvice
public class RestExceptionsHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionsHandler.class);

    private final CustomMessageSource messageSource;

    public RestExceptionsHandler(CustomMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(value = {ServerWebInputException.class})
    public ResponseEntity<ExceptionResponse> badRequest(ServerWebInputException ex) {
        logException(ex);
        return ResponseEntity.badRequest().body(
                new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), "Bad request"));
    }

    @ExceptionHandler(value = {ResourceAccessException.class})
    public ResponseEntity<ExceptionResponse> resoruceAccessException(ResourceAccessException ex) {
        logException(ex);
        return new ResponseEntity<>(new ExceptionResponse(HttpStatus.SERVICE_UNAVAILABLE.value(), "Connection error"), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(value = {DomainException.class})
    public ResponseEntity<ExceptionResponse> domainException(DomainException ex) {
        logException(ex);
        return new ResponseEntity<>(new ExceptionResponse(ex.errorCode, getMessage(ex)), ex.httpStatus);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ExceptionResponse> internalServerError(Exception ex) {
        logException(ex);
        return ResponseEntity.internalServerError().body(
                new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error"));
    }

    private String getMessage(DomainException ex) {
        var returnMessage = messageSource.getMessage(ex.getMessage());
        return returnMessage == null ? ex.getMessage() : returnMessage;
    }

    private void logException(Exception ex) {
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error("Exception", ex);
        }
    }
}
