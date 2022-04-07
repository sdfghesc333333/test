package com.asia.leadsgen.test.exception;

@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException {
    public NotFoundException(String cause) {
        super(cause);
    }
}
