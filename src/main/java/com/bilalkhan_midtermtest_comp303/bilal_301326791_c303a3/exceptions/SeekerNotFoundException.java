package com.bilalkhan_midtermtest_comp303.bilal_301326791_c303a3.exceptions;

public class SeekerNotFoundException extends RuntimeException {
    public SeekerNotFoundException(String message) {
        super(message);
    }

    public SeekerNotFoundException(Throwable cause) {
        super(cause);
    }

    public SeekerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
