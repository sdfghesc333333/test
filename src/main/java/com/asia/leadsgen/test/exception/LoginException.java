package com.asia.leadsgen.test.exception;

@SuppressWarnings("serial")
public class LoginException extends RuntimeException {
    public LoginException(String cause) {
        super(cause);
    }
}
