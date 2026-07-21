package com.github.jsf.scanners.delimiters;

import com.github.utilities.validators.Preconditions;

public record ComponentDelimiter(String open, String close) implements Delimiter {

    /**
     * Create a new Component Delimiter.
     *
     * @param open  The open delimiter for the component
     * @param close The close delimiter for the component
     * @throws CharacterNotAllowedException if the open or close delimiter contains not-allowed characters.
     * @throws NullPointerException         if the open or close delimiter is null or empty.
     */
    public ComponentDelimiter(String open, String close) {
        this.open = Delimiter.containsOnlyAllowedCharacters(Preconditions.parameterNotNull(open, "open").trim(), ILLEGAL_CHARS);
        this.close = Delimiter.containsOnlyAllowedCharacters(Preconditions.parameterNotNull(close, "close").trim(), ILLEGAL_CHARS);
    }
}
