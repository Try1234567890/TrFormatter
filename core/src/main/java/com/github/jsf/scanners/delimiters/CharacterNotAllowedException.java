package com.github.jsf.scanners.delimiters;

public class CharacterNotAllowedException extends RuntimeException {
    public CharacterNotAllowedException() {
    }

    public CharacterNotAllowedException(String message) {
        super(message);
    }

    public CharacterNotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CharacterNotAllowedException(Throwable cause) {
        super(cause);
    }

    public CharacterNotAllowedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
