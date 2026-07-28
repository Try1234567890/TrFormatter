package com.github.jsf.scanners.impls;

import com.github.jsf.scanners.IllegalComponentException;
import com.github.jsf.scanners.Scanner;
import com.github.jsf.scanners.ScannerOptions;
import com.github.jsf.scanners.beans.IndexedComponent;
import com.github.jsf.scanners.delimiters.Delimiter;
import com.github.jsf.text.Text;
import com.github.utilities.validators.Preconditions;

import java.util.Optional;

public class PrefixScanner extends Scanner<IndexedComponent> {
    private final String prefix;

    public PrefixScanner(Text text, ScannerOptions options, String prefix) throws NullPointerException, IllegalArgumentException {
        super(text, options);
        this.prefix = Preconditions.parameterNotNull(prefix, "prefix", "prefix cannot be null or empty.");
    }

    public PrefixScanner(Text text, String prefix) throws NullPointerException, IllegalArgumentException {
        super(text);
        this.prefix = Preconditions.parameterNotNull(prefix, "prefix", "prefix cannot be null or empty.");
    }


    public String getPrefix() {
        return prefix;
    }

    @Override
    public Optional<IndexedComponent> scanFirst(int offset, int length) throws IllegalComponentException {
        int index = text().indexOfNonBetween(getPrefix(), offset, length, options().EXCLUDERS.toArray(new Delimiter[0]));
        return create(index, length);
    }

    @Override
    public Optional<IndexedComponent> scanLast(int offset, int length) throws IllegalComponentException {
        int index = text().lastIndexOfNonBetween(getPrefix(), offset, length, options().EXCLUDERS.toArray(new Delimiter[0]));
        return create(index, length);
    }

    private Optional<IndexedComponent> create(int offset, int length) {
        if (offset == -1) return Optional.empty();

        int endIndex = text().indexOfFromNonBetween(" ", offset, options().EXCLUDERS.toArray(new Delimiter[0]));
        if (endIndex == -1 || endIndex >= length) endIndex = (length - 1); // Minus 1 because is exclusive.

        Text component = text().subtext(offset, endIndex);
        return Optional.of(new IndexedComponent(component, offset, endIndex));
    }

    @Override
    public boolean hasNext(int offset, int length) {
        return text().indexOfNonBetween(getPrefix(), offset, length, options().EXCLUDERS.toArray(new Delimiter[0])) != -1;
    }
}
