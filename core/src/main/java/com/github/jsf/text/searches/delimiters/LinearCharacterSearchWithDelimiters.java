package com.github.jsf.text.searches.delimiters;

import com.github.jsf.scanners.delimiters.Delimiter;
import com.github.jsf.text.Text;
import com.github.utilities.validators.Preconditions;

public final class LinearCharacterSearchWithDelimiters extends LinearSearchWithDelimiters {
    private final char character;

    private LinearCharacterSearchWithDelimiters(char[] text, char character, int offset, int length, Delimiter[] delimiters) {
        super(text, offset, length, delimiters);
        this.character = Preconditions.parameterNotNull(character, "character");
    }

    public static LinearCharacterSearchWithDelimiters of(char[] text, char character, int offset, int length, Delimiter... delimiters) {
        return new LinearCharacterSearchWithDelimiters(text, character, offset, length, delimiters);
    }

    public static LinearCharacterSearchWithDelimiters of(Text text, char character, int offset, int length, Delimiter... delimiters) {
        return new LinearCharacterSearchWithDelimiters(Preconditions.parameterNotNull(text, "text").toCharArray(), character, offset, length, delimiters);
    }

    public static LinearCharacterSearchWithDelimiters of(String text, char character, int offset, int length, Delimiter... delimiters) {
        return new LinearCharacterSearchWithDelimiters(Preconditions.parameterNotNull(text, "text").toCharArray(), character, offset, length, delimiters);
    }

    public char character() {
        return character;
    }

    @Override
    protected boolean matches(int offset) {
        return text()[offset] == character;
    }
}





















