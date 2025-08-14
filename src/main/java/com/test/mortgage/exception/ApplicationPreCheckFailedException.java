package com.test.mortgage.exception;

public class ApplicationPreCheckFailedException extends RuntimeException {
    public ApplicationPreCheckFailedException(String message) {
        super(message);
    }
}
