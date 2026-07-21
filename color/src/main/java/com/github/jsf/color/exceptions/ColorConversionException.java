package com.github.jsf.color.exceptions;

/**
 * This exception is thrown when a color conversion fails.
 */
public class ColorConversionException extends RuntimeException {
    public ColorConversionException() {
    }

    public ColorConversionException(String message) {
        super(message);
    }

    public ColorConversionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ColorConversionException(Throwable cause) {
        super(cause);
    }

    public ColorConversionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
