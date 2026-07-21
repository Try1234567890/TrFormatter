package com.github.jsf.text.searches.delimiters;

import com.github.jsf.scanners.delimiters.Delimiter;
import com.github.jsf.text.Text;
import com.github.utilities.validators.Preconditions;

public final class LinearSequenceSearchWithDelimiters extends LinearSearchWithDelimiters {
    private final char[] sequence;

    private LinearSequenceSearchWithDelimiters(char[] text, char[] sequence, int offset, int length, Delimiter[] delimiters) {
        super(text, offset, length, delimiters);
        this.sequence = Preconditions.parameterNotNull(sequence, "sequence");
    }

    public static LinearSequenceSearchWithDelimiters of(char[] text, String sequence, int offset, int length, Delimiter... delimiters) {
        return new LinearSequenceSearchWithDelimiters(
                text,
                Preconditions.parameterNotNull(sequence, "sequence").toCharArray(),
                offset,
                length,
                delimiters
        );
    }

    public static LinearSequenceSearchWithDelimiters of(Text text, String sequence, int offset, int length, Delimiter... delimiters) {
        return new LinearSequenceSearchWithDelimiters(
                Preconditions.parameterNotNull(text, "text").toCharArray(),
                Preconditions.parameterNotNull(sequence, "sequence").toCharArray(),
                offset,
                length,
                delimiters
        );
    }

    public static LinearSequenceSearchWithDelimiters of(String text, String sequence, int offset, int length, Delimiter... delimiters) {
        return new LinearSequenceSearchWithDelimiters(
                Preconditions.parameterNotNull(text, "text").toCharArray(),
                Preconditions.parameterNotNull(sequence, "sequence").toCharArray(),
                offset,
                length,
                delimiters
        );
    }

    public char[] sequence() {
        return sequence;
    }

    @Override
    protected boolean matches(int offset) {
        return matches(sequence, offset);
    }
}





















