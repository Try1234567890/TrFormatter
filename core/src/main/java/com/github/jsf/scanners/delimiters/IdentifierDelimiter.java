package com.github.jsf.scanners.delimiters;

import com.github.utilities.validators.Preconditions;

public record IdentifierDelimiter(String value) implements Delimiter {

    public IdentifierDelimiter(String value) {
        this.value = Delimiter.containsOnlyAllowedCharacters(Preconditions.parameterNotNull(value, "value").trim(), ILLEGAL_CHARS);
    }

    @Override
    public String open() {
        return value;
    }

    @Override
    public String close() {
        return value;
    }
}
