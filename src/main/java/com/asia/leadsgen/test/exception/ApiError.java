package com.asia.leadsgen.test.exception;

public class ApiError {
    private int status;
    private String message;

    public ApiError(int status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public ApiError(){};

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
