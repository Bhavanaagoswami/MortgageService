package com.test.mortgage.exception;

public class MortgageRateNotFound extends RuntimeException {
    public MortgageRateNotFound(String message) {
        super(message);
    }
}
