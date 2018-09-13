package com.srini.albumservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@ControllerAdvice
public class DefaultExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity defaultErrorHandler(Exception exception) {
        LOGGER.error("Encountered an exception while processing request", exception);
        return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity validationExceptionHandler(ValidationException exception) {
        LOGGER.error("Encountered an exception while processing request");
        return new ResponseEntity<>(exception.getError(), OK);
    }

}
