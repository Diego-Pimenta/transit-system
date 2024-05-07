package com.unifacs.transitsystem.exception;

public class MissingRequestHeaderException extends RuntimeException {

    public MissingRequestHeaderException(String msg) {
        super(msg);
    }
}
