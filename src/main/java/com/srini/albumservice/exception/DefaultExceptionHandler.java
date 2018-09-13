package com.srini.albumservice.exception;

import com.srini.albumservice.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@ControllerAdvice
public class DefaultExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity defaultErrorHandler(Exception exception) {
        LOGGER.error("Encountered an exception while processing request", exception);
        return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity fileNotFOundException(FileNotFoundException exception) {
        LOGGER.error("Encountered an exception while processing request");
        return new ResponseEntity<>(NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity validationExceptionHandler(ValidationException exception) {
        LOGGER.error("Encountered an exception while processing request");
        return new ResponseEntity(Response.withError(exception.getError()), OK);
    }

}
