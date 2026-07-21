package com.github.jsf.scanners;

public class IllegalComponentException extends RuntimeException {

    public IllegalComponentException() {
    }

    public IllegalComponentException(String message) {
        super(message);
    }

    public IllegalComponentException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalComponentException(Throwable cause) {
        super(cause);
    }

    public IllegalComponentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
