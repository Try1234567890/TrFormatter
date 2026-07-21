package com.github.jsf.text.searches;

import com.github.jsf.scanners.delimiters.Delimiter;
import com.github.jsf.text.Text;
import com.github.jsf.text.searches.delimiters.LinearCharacterSearchWithDelimiters;
import com.github.jsf.text.searches.delimiters.LinearSequenceSearchWithDelimiters;
import com.github.jsf.text.searches.delimiters.ReverseCharacterSearchWithDelimiters;
import com.github.jsf.text.searches.delimiters.ReverseSequenceSearchWithDelimiters;
import com.github.utilities.validators.Preconditions;

public abstract class Search {
    private final char[] text;
    private final int offset;
    private final int length;

    protected Search(char[] text, int offset, int length) {
        this.text = Preconditions.parameterNotNull(text, "text");
        Preconditions.check(offset >= 0, "offset must be >= 0. Offset: " + offset);
        Preconditions.check(length <= text.length, "length must be <= text.length. Length: " + length + " text.len: " + text.length);
        Preconditions.check(offset < length, "offset must be < length. Offset: " + offset + " length: " + length);

        this.offset = offset;
        this.length = length;
    }

    public char[] text() {
        return text;
    }

    public int offset() {
        return offset;
    }

    public int length() {
        return length;
    }

    /**
     * Search the sequence inside the {@link #text()} starting from {@code offset} (inclusive)
     * and ending at {@code length} (exclusive).
     *
     * @return The index of the first character of the first sequence found, or {@code -1} if none is found.
     */
    public abstract int search();

    public static LinearSequenceSearchWithDelimiters linear(char[] text, String sequence, int offset, int length, Delimiter... delimiters) {
        return LinearSequenceSearchWithDelimiters.of(text, sequence, offset, length, delimiters);
    }

    public static LinearSequenceSearchWithDelimiters linear(String text, String sequence, int offset, int length, Delimiter... delimiters) {
        return LinearSequenceSearchWithDelimiters.of(Preconditions.parameterNotNull(text, "text").toCharArray(), sequence, offset, length, delimiters);
    }

    public static LinearSequenceSearchWithDelimiters linear(Text text, String sequence, int offset, int length, Delimiter... delimiters) {
        return LinearSequenceSearchWithDelimiters.of(Preconditions.parameterNotNull(text, "text").toCharArray(), sequence, offset, length, delimiters);
    }

    public static LinearCharacterSearchWithDelimiters linear(char[] text, char character, int offset, int length, Delimiter... delimiters) {
        return LinearCharacterSearchWithDelimiters.of(text, character, offset, length, delimiters);
    }

    public static LinearCharacterSearchWithDelimiters linear(String text, char character, int offset, int length, Delimiter... delimiters) {
        return LinearCharacterSearchWithDelimiters.of(Preconditions.parameterNotNull(text, "text").toCharArray(), character, offset, length, delimiters);
    }

    public static LinearCharacterSearchWithDelimiters linear(Text text, char character, int offset, int length, Delimiter... delimiters) {
        return LinearCharacterSearchWithDelimiters.of(Preconditions.parameterNotNull(text, "text").toCharArray(), character, offset, length, delimiters);
    }

    public static ReverseSequenceSearchWithDelimiters reverse(char[] text, String sequence, int offset, int length, Delimiter... delimiters) {
        return ReverseSequenceSearchWithDelimiters.of(text, sequence, offset, length, delimiters);
    }

    public static ReverseSequenceSearchWithDelimiters reverse(String text, String sequence, int offset, int length, Delimiter... delimiters) {
        return ReverseSequenceSearchWithDelimiters.of(Preconditions.parameterNotNull(text, "text").toCharArray(), sequence, offset, length, delimiters);
    }

    public static ReverseSequenceSearchWithDelimiters reverse(Text text, String sequence, int offset, int length, Delimiter... delimiters) {
        return ReverseSequenceSearchWithDelimiters.of(Preconditions.parameterNotNull(text, "text").toCharArray(), sequence, offset, length, delimiters);
    }

    public static ReverseCharacterSearchWithDelimiters reverse(char[] text, char character, int offset, int length, Delimiter... delimiters) {
        return ReverseCharacterSearchWithDelimiters.of(text, character, offset, length, delimiters);
    }

    public static ReverseCharacterSearchWithDelimiters reverse(String text, char character, int offset, int length, Delimiter... delimiters) {
        return ReverseCharacterSearchWithDelimiters.of(Preconditions.parameterNotNull(text, "text").toCharArray(), character, offset, length, delimiters);
    }

    public static ReverseCharacterSearchWithDelimiters reverse(Text text, char character, int offset, int length, Delimiter... delimiters) {
        return ReverseCharacterSearchWithDelimiters.of(Preconditions.parameterNotNull(text, "text").toCharArray(), character, offset, length, delimiters);
    }

    /**
     * Checks if the {@link #text()} matches at the given {@code offset} with the
     * one of the given {@code sequences}.
     *
     * @param sequences The sequences to checks
     * @param offset    the offset to check the text at
     * @return the index of the sequence matched inside {@code sequences}, or {@code -1} if none matches.
     */
    protected int matchesAny(char[][] sequences, int offset) {
        return matchesAny(text(), sequences, offset);
    }

    /**
     * Checks if the {@link #text()} matches at the given {@code offset} with the
     * given {@code sequence}
     *
     * @param sequence the sequence to check for
     * @param offset   the offset to check the text at
     * @return {@code true} if it matches, otherwise {@code false}.
     */
    protected boolean matches(char[] sequence, int offset) {
        return matches(text(), sequence, offset);
    }

    public static boolean matches(char[] text, char[] sequence, int offset) {
        if ((text.length - offset) < sequence.length) return false;

        int index = 0;

        while (index < sequence.length) {
            char textCh = text[(offset + index)];
            char sequenceCh = sequence[index];

            if (textCh != sequenceCh) {
                return false;
            } else index++;
        }

        return true;
    }

    public static int matchesAny(char[] text, char[][] sequences, int offset) {
        for (int i = 0; i < sequences.length; i++) {
            char[] sequence = sequences[i];
            if (matches(text, sequence, offset)) {
                return i;
            }

        }

        return -1;
    }
}
