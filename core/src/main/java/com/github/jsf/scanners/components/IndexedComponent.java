package com.github.jsf.scanners.components;

import com.github.jsf.text.Text;
import com.github.utilities.validators.Preconditions;

/**
 * An indexed component represent a portion of a text that contains the entire component,
 * from the start index to the end index (both included).
 * <p>
 * The indexed component contains the text where the component has been found too.
 */
public class IndexedComponent {
    private final Text component;
    private final int start;
    private final int end;

    /**
     * Create a new indexed component.
     *
     * @param component The text part that contains the component text.
     * @param start     The start index of the component part inside the {@code text}
     * @param end       The end index of the component part inside the {@code text}
     * @throws IllegalArgumentException if start major than end, start minus than 0, end major than text.length() and implicitly if the end minus than 0
     * @throws NullPointerException     if the text or component is null
     */
    public IndexedComponent(Text component, int start, int end) {
        // If start > 0 check passes, the end cannot be minus then start, so the end index minus values is considered valid.
        Preconditions.check(start >= 0, "The start index must be greater than or equal to 0");
        Preconditions.check(start <= end, "The start index must be less than or equal to the end index");

        this.component = component;
        this.start = start;
        this.end = end;
    }

    /**
     * Retrieve the text part that contains the component text.
     *
     * @return The text part that contains the component text.
     */
    public Text getComponent() {
        return component;
    }

    /**
     * Retrieve the start index of the component text inside the text.
     *
     * @return the start index of the component text inside the text.
     */
    public int start() {
        return start;
    }

    /**
     * Retrieve the end index of the component text inside the text.
     *
     * @return the end index of the component text inside the text.
     */
    public int end() {
        return end;
    }

    @Override
    public String toString() {
        return "IndexedComponent{Component: '" + component + "', Start: " + start + ", End: " + end + '}';
    }
}
