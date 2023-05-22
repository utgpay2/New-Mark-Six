package com.central.common.exception;


public class TokenNotValidException
        extends RuntimeException {
    private static final long serialVersionUID = 6610083281801529147L;

    public TokenNotValidException(String message) {
        super(message);
    }
}


