package com.github.jsf.text.searches.delimiters;

import com.github.jsf.scanners.delimiters.Delimiter;
import com.github.jsf.text.Text;
import com.github.utilities.validators.Preconditions;

public final class ReverseCharacterSearchWithDelimiters extends ReverseSearchWithDelimiters {
    private final char character;

    private ReverseCharacterSearchWithDelimiters(char[] text, char character, int offset, int length, Delimiter[] delimiters) {
        super(text, offset, length, delimiters);
        this.character = Preconditions.parameterNotNull(character, "character");
    }

    public static ReverseCharacterSearchWithDelimiters of(char[] text, char character, int offset, int length, Delimiter... delimiters) {
        return new ReverseCharacterSearchWithDelimiters(text, character, offset, length, delimiters);
    }

    public static ReverseCharacterSearchWithDelimiters of(Text text, char character, int offset, int length, Delimiter... delimiters) {
        return new ReverseCharacterSearchWithDelimiters(Preconditions.parameterNotNull(text, "text").toCharArray(), character, offset, length, delimiters);
    }

    public static ReverseCharacterSearchWithDelimiters of(String text, char character, int offset, int length, Delimiter... delimiters) {
        return new ReverseCharacterSearchWithDelimiters(Preconditions.parameterNotNull(text, "text").toCharArray(), character, offset, length, delimiters);
    }

    public char character() {
        return character;
    }

    @Override
    protected boolean matches(int offset) {
        return text()[offset] == character;
    }
}





















