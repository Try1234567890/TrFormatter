package com.github.jsf.scanners.impls;

import com.github.jsf.scanners.IllegalComponentException;
import com.github.jsf.scanners.Scanner;
import com.github.jsf.scanners.ScannerOptions;
import com.github.jsf.scanners.components.IndexedComponent;
import com.github.jsf.scanners.delimiters.Delimiter;
import com.github.jsf.text.Text;
import com.github.utilities.validators.Preconditions;

import java.util.Optional;

public class DelimiterScanner extends Scanner<IndexedComponent> {
    private final Delimiter delimiter;

    public DelimiterScanner(Text text, ScannerOptions options, Delimiter delimiter) throws NullPointerException, IllegalArgumentException {
        super(text, options);
        this.delimiter = Preconditions.parameterNotNull(delimiter, "delimiter");
    }

    public DelimiterScanner(Text text, Delimiter delimiter) throws NullPointerException, IllegalArgumentException {
        super(text);
        this.delimiter = Preconditions.parameterNotNull(delimiter, "delimiter");
    }


    public Delimiter getDelimiter() {
        return delimiter;
    }

    protected String open() {
        return getDelimiter().open();
    }

    protected String close() {
        return getDelimiter().close();
    }

    @Override
    public Optional<IndexedComponent> scanFirst(int offset, int length) throws IllegalComponentException {
        Delimiter[] excluders = options().EXCLUDERS.toArray(new Delimiter[0]);
        Text text = text();
        String open = open(), close = close();

        int tail = text.indexOfNonBetween(open, offset, length, excluders);
        int head = text.indexOfNonBetween(close, (tail + open.length()), length, excluders) + close.length();
        return create(tail, head);
    }

    @Override
    public Optional<IndexedComponent> scanLast(int offset, int length) throws IllegalComponentException {
        Delimiter[] excluders = options().EXCLUDERS.toArray(new Delimiter[0]);
        Text text = text();
        String open = open(), close = close();

        int head = text.lastIndexOfNonBetween(close, offset, length, excluders);
        int tail = text.lastIndexOfNonBetween(open, (head - close.length()), length, excluders) + open.length();
        return create(tail, head);
    }

    protected Optional<IndexedComponent> create(int tail, int head) {
        if (tail == -1) {
            // No component is found.
            return Optional.empty();
        }

        Text text = text();
        String close = close();
        String open = open();

        if (head == -1) {
            throw new IllegalComponentException("The component at index " + tail + " near " + getContext(tail) +
                    " is not closed correctly with the delimiter \"" + close + "\".");
        } else if (head == (close.length() - 1)) {
            throw new IllegalComponentException("The component at index " + tail + " near " + getContext(tail) +
                    " is not opened correctly with the delimiter \"" + open + "\".");
        }

        Text subtext = text.subtext(tail, head);
        return Optional.of(new IndexedComponent(subtext, tail, head));
    }

    @Override
    public boolean hasNext(int offset, int length) {
        return text().indexOfNonBetween(open(), offset, length, options().EXCLUDERS.toArray(new Delimiter[0])) != -1;
    }

    @Override
    public String toString() {
        return super.toString() + " with delimiter " + delimiter;
    }

    @Override
    public String toString(int to) {
        return super.toString(to) + " with delimiter " + delimiter;
    }
}
