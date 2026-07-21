package com.github.jsf.scanners.impls;

import com.github.jsf.scanners.ScannerOptions;
import com.github.jsf.scanners.delimiters.Delimiter;
import com.github.jsf.scanners.IllegalComponentException;
import com.github.jsf.scanners.Scanner;
import com.github.jsf.scanners.components.IndexedComponent;
import com.github.jsf.scanners.delimiters.IdentifierDelimiter;
import com.github.jsf.scanners.delimiters.StringDelimiter;
import com.github.jsf.text.Text;
import com.github.utilities.validators.Preconditions;

import java.util.List;
import java.util.Optional;

public class SplitterScanner extends Scanner<IndexedComponent> {
    private final StringDelimiter splitter;

    public SplitterScanner(Text text, ScannerOptions options, StringDelimiter splitter) throws NullPointerException, IllegalArgumentException {
        super(text, options);
        this.splitter = Preconditions.parameterNotNull(splitter, "splitter");
    }

    public SplitterScanner(Text text, StringDelimiter splitter) throws NullPointerException, IllegalArgumentException {
        super(text);
        this.splitter = Preconditions.parameterNotNull(splitter, "splitter");
    }


    public StringDelimiter getSplitter() {
        return splitter;
    }

    @Override
    public Optional<IndexedComponent> scanFirst(int offset, int length) throws IllegalComponentException {
        String splitStr = getSplitter().value();
        int splitterIndex = text().indexOfNonBetween(splitStr, offset, length, options().EXCLUDERS.toArray(new Delimiter[0]));
        if (splitterIndex == -1) return Optional.empty();

        Text component = text().subtext(offset, splitterIndex);
        return Optional.of(new IndexedComponent(component, offset, splitterIndex));
    }

    @Override
    public Optional<IndexedComponent> scanLast(int offset, int length) throws IllegalComponentException {
        String splitStr = getSplitter().value();
        int splitterIndex = text().lastIndexOfNonBetween(splitStr, offset, length, options().EXCLUDERS.toArray(new Delimiter[0]));
        if (splitterIndex == -1) return Optional.empty();
        int componentStart = splitterIndex - splitStr.length();

        Text component = text().subtext(componentStart, length);
        return Optional.of(new IndexedComponent(component, componentStart, length));
    }

    @Override
    public List<IndexedComponent> scanAll(int offset, int length) throws IllegalComponentException {
        List<IndexedComponent> components = super.scanAll(offset, length);
        if (components.isEmpty()) return components;

        int head = components.getLast().end();
        if (head <= length) {
            Text component = text().subtext(head, length);
            components.add(new IndexedComponent(component, head, length));
        }

        return components;
    }

    @Override
    public boolean hasNext(int offset, int length) {
        return text().indexOfNonBetweenStrings(getSplitter().value(), offset, length) != -1;
    }
}