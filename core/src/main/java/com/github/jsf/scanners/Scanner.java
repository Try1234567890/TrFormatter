package com.github.jsf.scanners;

import com.github.jsf.scanners.beans.IndexedComponent;
import com.github.jsf.text.Text;
import com.github.utilities.validators.Preconditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class Scanner<C extends IndexedComponent> {
    public static final int CONTEXT_LENGTH = 20;
    private final Text text;
    private final ScannerOptions options;
    private int componentCount;

    public Scanner(Text text, ScannerOptions options) throws NullPointerException, IllegalArgumentException {
        this.text = Preconditions.parameterNotNull(text, "text");
        this.options = Preconditions.simpleNotNull(options, new ScannerOptions());
    }

    public Scanner(Text text) throws NullPointerException, IllegalArgumentException {
        this(text, new ScannerOptions());
    }

    public Text text() {
        return text;
    }

    public ScannerOptions options() {
        return options;
    }

    /**
     * Scan the first component found inside the subtext that starts at the {@code offset}
     * (inclusive) and ends at {@code length} (exclusive) from the left to the right.
     *
     * @param offset The index relative to {@link #text} where starts re-searching (inclusive).
     * @param length The index relative to {@link #text} where ends re-searching (exclusive).
     * @return the first component found inside the subtext, or {@link Optional#empty()} if no component is found.
     * @throws IllegalComponentException if a component is found, but it is not valid.
     */
    public abstract Optional<C> scanFirst(int offset, int length) throws IllegalComponentException;


    /**
     * Scan the first component found inside the subtext that starts at the {@code offset}
     * (inclusive) from the left to the right.
     *
     * @param offset The index relative to {@link #text} where starts re-searching (inclusive).
     * @return the first component found inside the subtext, or {@link Optional#empty()} if no component is found.
     * @throws IllegalComponentException if a component is found, but it is not valid.
     */
    public Optional<C> scanFirstFrom(int offset) {
        return scanFirst(offset, text.length());
    }

    /**
     * Scan the first component found inside the subtext that and ends at {@code length}
     * (exclusive) from the left to the right.
     *
     * @param length The index relative to {@link #text} where ends re-searching (exclusive).
     * @return the first component found inside the subtext, or {@link Optional#empty()} if no component is found.
     * @throws IllegalComponentException if a component is found, but it is not valid.
     */
    public Optional<C> scanFirstTo(int length) {
        return scanFirst(0, length);
    }

    /**
     * Scan the first component found inside the {@link #text} from the left to the right.
     *
     * @return the first component found inside the subtext, or {@link Optional#empty()} if no component is found.
     * @throws IllegalComponentException if a component is found, but it is not valid.
     */
    public Optional<C> scanFirst() {
        return scanFirst(0, text.length());
    }

    /**
     * Scan the last component found inside the subtext that starts at the {@code offset}
     * (inclusive) and ends at {@code length} (exclusive) from the right to the left.
     *
     * @param offset The index relative to {@link #text} where starts re-searching (inclusive).
     * @param length The index relative to {@link #text} where ends re-searching (exclusive).
     * @return the last component found inside the subtext, or {@link Optional#empty()} if no component is found.
     * @throws IllegalComponentException if a component is found, but it is not valid.
     */
    public abstract Optional<C> scanLast(int offset, int length) throws IllegalComponentException;

    /**
     * Scan the last component found inside the subtext that starts at the {@code offset}
     * (inclusive) from the right to the left.
     *
     * @param offset The index relative to {@link #text} where starts re-searching (inclusive).
     * @return the last component found inside the subtext, or {@link Optional#empty()} if no component is found.
     * @throws IllegalComponentException if a component is found, but it is not valid.
     */
    public Optional<C> scanLastFrom(int offset) throws IllegalComponentException {
        return scanLast(offset, text.length());
    }

    /**
     * Scan the last component found inside the subtext that and ends at {@code length}
     * (exclusive) from the right to the left.
     *
     * @param length The index relative to {@link #text} where ends re-searching (exclusive).
     * @return the last component found inside the subtext, or {@link Optional#empty()} if no component is found.
     * @throws IllegalComponentException if a component is found, but it is not valid.
     */
    public Optional<C> scanLastTo(int length) throws IllegalComponentException {
        return scanLast(0, length);
    }

    /**
     * Scan the first component found inside the {@link #text} from the left to the right.
     *
     * @return the first component found inside the subtext, or {@link Optional#empty()} if no component is found.
     * @throws IllegalComponentException if a component is found, but it is not valid.
     */
    public Optional<C> scanLast() throws IllegalComponentException {
        return scanLast(0, text.length());
    }

    /**
     * Scan all the components found in the subtext that starts at the {@code offset} (inclusive)
     * and ends at {@code length} (exclusive) from the left to the right.
     *
     * @param offset The index relative to {@link #text} where starts re-searching (inclusive).
     * @param length The index relative to {@link #text} where ends re-searching (exclusive).
     * @return the last component found inside the subtext, or {@link Optional#empty()} if no component is found.
     * @throws IllegalComponentException if a component is found, but it is not valid.
     */
    public List<C> scanAll(int offset, int length) throws IllegalComponentException {
        List<C> components = new ArrayList<>();
        int i = offset;
        Optional<C> maybeComponent;

        while ((maybeComponent = scanFirst(i, length)).isPresent()) {
            C component = maybeComponent.get();
            components.add(component);
            i = component.end();
            if (i >= length) break;
        }

        return components;
    }

    /**
     * Scan all the components found in the subtext that starts at the {@code offset} (inclusive) from the left to the right.
     *
     * @param offset The index relative to {@link #text} where starts re-searching (inclusive).
     * @return the last component found inside the subtext, or {@link Optional#empty()} if no component is found.
     * @throws IllegalComponentException if a component is found, but it is not valid.
     */
    public List<C> scanAllFrom(int offset) throws IllegalComponentException {
        return scanAll(offset, text.length());
    }

    /**
     * Scan all the components found in the subtext that ends at {@code length} (exclusive) from the left to the right.
     *
     * @param length The index relative to {@link #text} where ends re-searching (exclusive).
     * @return the last component found inside the subtext, or {@link Optional#empty()} if no component is found.
     * @throws IllegalComponentException if a component is found, but it is not valid.
     */
    public List<C> scanAllTo(int length) throws IllegalComponentException {
        return scanAll(0, length);
    }

    /**
     * Scan all the components found in text from the left to the right.
     *
     * @return the last component found inside the subtext, or {@link Optional#empty()} if no component is found.
     * @throws IllegalComponentException if a component is found, but it is not valid.
     */
    public List<C> scanAll() throws IllegalComponentException {
        List<C> components = scanAll(0, text.length());
        this.componentCount = components.size(); // Cache the components count.
        return components;
    }

    /**
     * Checks if a component is present in the subtext that starts at {@code offset} (inclusive)
     * and ends at {@code length} (exclusive) from the left to the right.
     *
     * @param offset The index relative to {@link #text} where starts re-searching (inclusive).
     * @param length The index relative to {@link #text} where ends re-searching (exclusive).
     * @return {@code true} if is found, otherwise {@code false}.
     */
    public abstract boolean hasNext(int offset, int length);

    /**
     * Checks if a component is present in the subtext that starts at {@code offset} (inclusive)
     * from the left to the right.
     *
     * @param offset The index relative to {@link #text} where starts re-searching (inclusive).
     * @return {@code true} if is found, otherwise {@code false}.
     */
    public boolean hasNextFrom(int offset) {
        return hasNext(offset, text.length());
    }

    /**
     * Checks if a component is present in the subtext that ends at {@code length} (exclusive) from the left to the right.
     *
     * @param length The index relative to {@link #text} where ends re-searching (exclusive).
     * @return {@code true} if is found, otherwise {@code false}.
     */
    public boolean hasNextTo(int length) {
        return hasNext(0, length);
    }

    /**
     * Checks if a component is present in the text from the left to the right.
     *
     * @return {@code true} if is found, otherwise {@code false}.
     */
    public boolean hasNext() {
        return hasNext(0, text.length());
    }

    public int getComponentCount() {
        return componentCount < 0 ? (this.componentCount = scanAll().size()) : componentCount;
    }

    protected Text getContext(int offset) {
        return text.truncate(Math.max(0, (offset - CONTEXT_LENGTH)), Math.min(text.length(), (offset + CONTEXT_LENGTH)), "...").quoteWithDouble();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " of text " + text;
    }

    public String toString(int to) {
        return getClass().getSimpleName() + " of text " + text.truncate(to, CONTEXT_LENGTH, "...").quoteWithDouble();
    }
}














































