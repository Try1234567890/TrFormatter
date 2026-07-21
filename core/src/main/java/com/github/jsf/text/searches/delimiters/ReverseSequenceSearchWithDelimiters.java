package com.github.jsf.text.searches.delimiters;

import com.github.jsf.scanners.delimiters.Delimiter;
import com.github.jsf.text.Text;
import com.github.utilities.validators.Preconditions;

public final class ReverseSequenceSearchWithDelimiters extends ReverseSearchWithDelimiters {
    private final char[] sequence;

    private ReverseSequenceSearchWithDelimiters(char[] text, char[] sequence, int offset, int length, Delimiter[] delimiters) {
        super(text, offset, length, delimiters);
        this.sequence = Preconditions.parameterNotNull(sequence, "sequence");
    }

    public static ReverseSequenceSearchWithDelimiters of(char[] text, String sequence, int offset, int length, Delimiter... delimiters) {
        return new ReverseSequenceSearchWithDelimiters(
                text,
                Preconditions.parameterNotNull(sequence, "sequence").toCharArray(),
                offset,
                length,
                delimiters
        );
    }

    public static ReverseSequenceSearchWithDelimiters of(Text text, String sequence, int offset, int length, Delimiter... delimiters) {
        return new ReverseSequenceSearchWithDelimiters(
                Preconditions.parameterNotNull(text, "text").toCharArray(),
                Preconditions.parameterNotNull(sequence, "sequence").toCharArray(),
                offset,
                length,
                delimiters
        );
    }

    public static ReverseSequenceSearchWithDelimiters of(String text, String sequence, int offset, int length, Delimiter... delimiters) {
        return new ReverseSequenceSearchWithDelimiters(
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





















