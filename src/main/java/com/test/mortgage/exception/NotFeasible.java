package com.test.mortgage.exception;

public class NotFeasible extends RuntimeException {
    public NotFeasible(String message) {
        super(message);
    }
}
