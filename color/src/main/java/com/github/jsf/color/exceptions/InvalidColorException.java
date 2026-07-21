package com.github.jsf.color.exceptions;

/**
 * This exception is thrown when a color is not valid,
 * or more specifically, when the validation of a color fails.
 */
public class InvalidColorException extends RuntimeException {

    public InvalidColorException() {
    }

    public InvalidColorException(String message) {
        super(message);
    }

    public InvalidColorException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidColorException(Throwable cause) {
        super(cause);
    }

    public InvalidColorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
