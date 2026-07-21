package com.github.jsf.dynamic_placeholders.phases.resolution.scanner;

import com.github.jsf.dynamic_placeholders.phases.resolution.scanner.beans.DPDelimiterSet;
import com.github.jsf.dynamic_placeholders.phases.resolution.scanner.beans.IndexedDPComponent;
import com.github.jsf.scanners.IllegalComponentException;
import com.github.jsf.scanners.Scanner;
import com.github.jsf.scanners.ScannerOptions;
import com.github.jsf.scanners.components.IndexedComponent;
import com.github.jsf.scanners.impls.DelimiterScanner;
import com.github.jsf.text.Text;
import com.github.utilities.validators.Preconditions;

import java.util.List;
import java.util.Optional;

/**
 * DPScanner, that stands for {@code Dynamic Placeholder Scanner}, is the scanner
 * that peeks all the DynamicPlaceholders text portions and creates
 * a {@link IndexedDPComponent} for each.
 * <p>
 * It implements a {@code Balance Delimiters Search} for searching {@code Dynamic Placeholders}
 * to allow the use of others {@code Dynamic Placeholders} as parameters value of any component.
 */
public class DPScanner extends Scanner<IndexedDPComponent> {
    private final DelimiterScanner scanner;
    private final DPDelimiterSet set;

    public DPScanner(Text text, DPDelimiterSet set) throws NullPointerException, IllegalArgumentException {
        super(text);
        this.scanner = new DelimiterScanner(text, new ScannerOptions()
                .newExcluder(set.getActions())
                .newExcluder(set.getConditions())
                .newExcluder(set.getFunctions()), set.getPlaceholders());
        this.set = Preconditions.parameterNotNull(set, "set");
    }

    @Override
    public Optional<IndexedDPComponent> scanFirst(int offset, int length) throws IllegalComponentException {
        return scanner.scanFirst(offset, length).map(this::scanPlaceholder);
    }

    @Override
    public Optional<IndexedDPComponent> scanLast(int offset, int length) throws IllegalComponentException {
        return scanner.scanLast(offset, length).map(this::scanPlaceholder);
    }

    @Override
    public boolean hasNext(int offset, int length) {
        return scanner.hasNext(offset, length);
    }

    private IndexedDPComponent scanPlaceholder(IndexedComponent component) {
        IndexedComponent action = scanAction(component);
        List<IndexedComponent> conditions = scanConditions(component);
        List<IndexedComponent> functions = scanFunctions(component);

        return new IndexedDPComponent(component.getComponent(), component.start(), component.end(), action, conditions, functions);
    }

    private IndexedComponent scanAction(IndexedComponent placeholder) throws IllegalComponentException {
        List<IndexedComponent> rawActions = new DelimiterScanner(placeholder.getComponent(), set.getActions()).scanAll();

        if (rawActions.size() != 1) {
            int start = placeholder.start();
            int end = placeholder.end();
            throw new IllegalComponentException("A placeholder must have exactly one action. The placeholder at "
                    + "[" + start + " -> " + end + "]: " + text().truncate(start, end, "...").quoteWithDouble() + " has " + rawActions.size() + " actions.");
        }

        return rawActions.getFirst();
    }

    private List<IndexedComponent> scanConditions(IndexedComponent placeholder) throws IllegalComponentException {
        return new DelimiterScanner(placeholder.getComponent(), set.getConditions()).scanAll();
    }

    private List<IndexedComponent> scanFunctions(IndexedComponent placeholder) throws IllegalComponentException {
        return new DelimiterScanner(placeholder.getComponent(), set.getFunctions()).scanAll();
    }
}