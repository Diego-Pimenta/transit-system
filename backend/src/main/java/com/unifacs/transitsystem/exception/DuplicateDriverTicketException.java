package com.unifacs.transitsystem.exception;

public class DuplicateDriverTicketException extends RuntimeException {

    public DuplicateDriverTicketException(String message) {
        super(message);
    }
}
