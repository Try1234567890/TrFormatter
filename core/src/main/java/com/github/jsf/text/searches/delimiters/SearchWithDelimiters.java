package com.github.jsf.text.searches.delimiters;

import com.github.jsf.scanners.delimiters.Delimiter;
import com.github.jsf.text.searches.Search;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public sealed abstract class SearchWithDelimiters extends Search
        permits LinearSearchWithDelimiters, ReverseSearchWithDelimiters {
    private final char[][] openDelimiters;
    private final char[][] closeDelimiters;


    protected SearchWithDelimiters(char[] text, int offset, int length, Delimiter[] delimiters) {
        super(text, offset, length);
        Delimiters extract = extract(delimiters);
        this.openDelimiters = extract.opens;
        this.closeDelimiters = extract.closes;
    }

    private static Delimiters extract(Delimiter[] delimiters) {
        char[][] opens = new char[delimiters.length][];
        char[][] closes = new char[delimiters.length][];

        for (int i = 0; i < delimiters.length; i++) {
            Delimiter delimiter = delimiters[i];
            opens[i] = delimiter.open().toCharArray();
            closes[i] = delimiter.close().toCharArray();
        }

        return new Delimiters(opens, closes);
    }

    protected char[][] openDelimiters() {
        return openDelimiters;
    }

    protected char[][] closeDelimiters() {
        return closeDelimiters;
    }

    /* **---------------**
     *    Utility Beans
     * **---------------**
     */
    private record Delimiters(char[][] opens, char[][] closes) {

    }

    protected void handlePointer(List<DelimiterPointer> pointers, char[] open, char[] close) {
        int posInList;
        if ((posInList = findPointerIndex(pointers, open, close)) != -1) {
            DelimiterPointer pointer = pointers.get(posInList);
            pointer.plusDepth();
        } else {
            DelimiterPointer newPointer = new DelimiterPointer(open, close);
            pointers.add(newPointer);
        }
    }

    protected int findPointerIndex(List<DelimiterPointer> pointers, char[] openDel, char[] closeDel) {
        int size = pointers.size();
        for (int i = 0; i < size; i++) {
            DelimiterPointer pointer = pointers.get(i);
            if (pointer.openDelimiter() == openDel
                    && pointer.closeDelimiter() == closeDel) {
                return i;
            }
        }
        return -1;
    }

    public static final class DelimiterPointer {
        private final char[] openDelimiter;
        private final char[] closeDelimiter;
        private int depth;

        public DelimiterPointer(char[] openDelimiter, char[] closeDelimiter) {
            this.openDelimiter = openDelimiter;
            this.closeDelimiter = closeDelimiter;
            this.depth = 1;
        }

        public char[] openDelimiter() {
            return openDelimiter;
        }

        public char[] closeDelimiter() {
            return closeDelimiter;
        }

        public int depth() {
            return depth;
        }

        public void plusDepth() {
            this.depth += 1;
        }

        public void minusDepth() {
            this.depth -= 1;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof DelimiterPointer pointer)) return false;
            return Arrays.equals(openDelimiter, pointer.openDelimiter) && Arrays.equals(closeDelimiter, pointer.closeDelimiter);
        }

        @Override
        public int hashCode() {
            return Objects.hash(Arrays.hashCode(openDelimiter), Arrays.hashCode(closeDelimiter));
        }

        @Override
        public String toString() {
            return "DelimiterPointer{Open: " + Arrays.toString(openDelimiter) + ", Close: " + Arrays.toString(closeDelimiter) + ", Depth: " + depth + '}';
        }
    }
}
