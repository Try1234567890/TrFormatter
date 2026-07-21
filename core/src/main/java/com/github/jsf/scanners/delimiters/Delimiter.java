package com.github.jsf.scanners.delimiters;

/**
 * A delimiter is a couple of char sequences that delimits a portion of a
 * text inside the text itself.
 * In general, the delimited text is called "component".
 */
public interface Delimiter {
    String ILLEGAL_CHARS = "\"'~` ";

    /**
     * Returns the opening delimiter.
     */
    String open();

    /**
     * Returns the closing delimiter.
     */
    String close();

    /**
     * Checks if the given object is equal to this delimiter.
     */
    boolean equals(Object obj);

    @Override
    int hashCode();

    @Override
    String toString();

    static ComponentDelimiter of(String open, String close) {
        return new ComponentDelimiter(open, close);
    }

    static StringDelimiter of(String value) {
        return new StringDelimiter(value);
    }

    static IdentifierDelimiter ofID(String value) {
        return new IdentifierDelimiter(value);
    }

    /**
     * Helper method to checks if the {@code delimiter} contains only
     * allowed characters and throws an {@link CharacterNotAllowedException} if not.
     *
     * @param delimiter  The delimiter to check.
     * @param notAllowed The non-allowed characters.
     * @return The delimiter if it contains only allowed characters.
     * @throws CharacterNotAllowedException if the delimiter contains not-allowed characters.
     */
    static String containsOnlyAllowedCharacters(String delimiter,
                                                String notAllowed) throws CharacterNotAllowedException {
        for (char c : delimiter.toCharArray()) {
            if (notAllowed.indexOf(Character.toLowerCase(c)) != -1) {
                throw new CharacterNotAllowedException("The character '" + c + "' found inside the delimiter " + delimiter +
                        " is not allowed. Please remove it and try again.");
            }
        }
        return delimiter;
    }
}
