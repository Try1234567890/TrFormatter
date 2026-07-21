package com.github.jsf.dynamic_placeholders.phases.resolution.lexer.tokens;

import java.util.Optional;

public class Token {
    private final TokenType type;
    private final String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public Token(TokenType type) {
        this.type = type;
        this.value = null;
    }

    public TokenType type() {
        return type;
    }

    public Optional<String> value() {
        return Optional.ofNullable(value);
    }

    @Override
    public String toString() {
        return "Token{Type: " + type + ", Value: '" + value + "'}";
    }
}
