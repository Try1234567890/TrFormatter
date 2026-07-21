package com.github.jsf.dynamic_placeholders.names;

/**
 * This exception is thrown if two component have the same UID name.
 */
public class DuplicateNameException extends RuntimeException {
    public DuplicateNameException() {
    }

    public DuplicateNameException(String message) {
        super(message);
    }

    public DuplicateNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateNameException(Throwable cause) {
        super(cause);
    }

    public DuplicateNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
