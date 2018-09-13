package com.srini.albumservice.exception;

import com.srini.albumservice.response.Error;

public class ValidationException extends RuntimeException {

    private Error error;

    public ValidationException(Error error) {
        this.error = error;
    }

    Error getError() {
        return error;
    }
}
