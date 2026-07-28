package com.github.jsf.dynamic_placeholders.phases.resolution.scanner.beans;

import com.github.jsf.scanners.beans.IndexedComponent;
import com.github.jsf.text.Text;

import java.util.List;

public class IndexedDPComponent extends IndexedComponent {
    private final IndexedComponent action;
    private final List<IndexedComponent> conditions;
    private final List<IndexedComponent> functions;



    /**
     * Create a new indexed component.
     *
     * @param component The text part that contains the component text.
     * @param start     The start index of the component part inside the {@code text}
     * @param end       The end index of the component part inside the {@code text}
     * @throws IllegalArgumentException if start major than end, start minus than 0, end major than text.length() and implicitly if the end minus than 0
     * @throws NullPointerException     if the text or component is null
     */
    public IndexedDPComponent(Text component, int start, int end,
                              IndexedComponent action,
                              List<IndexedComponent> conditions,
                              List<IndexedComponent> functions) {
        super(component, start, end);
        this.action = action;
        this.conditions = conditions;
        this.functions = functions;
    }

    public IndexedComponent action() {
        return action;
    }

    public List<IndexedComponent> conditions() {
        return conditions;
    }

    public List<IndexedComponent> functions() {
        return functions;
    }
}
