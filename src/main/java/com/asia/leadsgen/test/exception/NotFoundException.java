package com.asia.leadsgen.test.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String cause) {
        super(cause);
    }
}
