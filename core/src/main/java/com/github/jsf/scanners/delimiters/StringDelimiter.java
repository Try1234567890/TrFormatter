package com.github.jsf.scanners.delimiters;

import com.github.utilities.validators.Preconditions;

public record StringDelimiter(String value) implements Delimiter {

    public StringDelimiter(String value) {
        this.value = Preconditions.parameterNotNull(value, "value").trim();
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
