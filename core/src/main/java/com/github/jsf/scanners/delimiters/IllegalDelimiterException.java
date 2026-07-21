package com.github.jsf.scanners.delimiters;

/**
 * This exception is thrown when an illegal delimiter is found.
 * For example if a specific type of delimiter is expected, but another type is found.
 */
public class IllegalDelimiterException extends RuntimeException {

    public IllegalDelimiterException() {
    }

    public IllegalDelimiterException(String message) {
        super(message);
    }

    public IllegalDelimiterException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalDelimiterException(Throwable cause) {
        super(cause);
    }

    public IllegalDelimiterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}