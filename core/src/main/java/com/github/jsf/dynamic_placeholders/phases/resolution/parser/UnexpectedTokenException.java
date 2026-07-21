package com.github.jsf.dynamic_placeholders.phases.resolution.parser;

/**
 * This exception is thrown when an unexpected token is found.
 */
public class UnexpectedTokenException extends RuntimeException {

    public UnexpectedTokenException() {
    }

    public UnexpectedTokenException(String message) {
        super(message);
    }

    public UnexpectedTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnexpectedTokenException(Throwable cause) {
        super(cause);
    }

    public UnexpectedTokenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
