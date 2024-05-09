package com.unifacs.transitsystem.exception;

public class RequestHeaderMissingException extends RuntimeException {

    public RequestHeaderMissingException(String message) {
        super(message);
    }
}
