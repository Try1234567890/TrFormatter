package com.github.jsf.dynamic_placeholders.components.impls.conditions;

import com.github.jsf.text.searches.Search;

import java.util.Optional;

public enum Comparator {

    MINOR("<"),
    MINOR_OR_EQUAL("<="),
    EQUALS("="),
    GREATER_OR_EQUAL(">="),
    GREATER(">");

    private final String symbol;

    Comparator(String symbol) {
        this.symbol = symbol;
    }

    public String symbol() {
        return symbol;
    }

    public static Optional<Comparator> matches(String symbol) {
        for (Comparator comparator : values()) {
            String currName = comparator.name();
            String currSymbol = comparator.symbol;
            if (symbol.equals(currSymbol) || symbol.equalsIgnoreCase(currName)) return Optional.of(comparator);
        }
        return Optional.empty();
    }

    /**
     * Tries to parse a comparator from the first characters of the given text.
     * For example, if we consider the {@link IfDate} condition, its 'date'
     * parameters can be something like this:
     * <pre>
     *     {@code @[if_date(date='>=18/07/2026')] // The comparator will be '>='}
     * </pre>
     * or otherwise the dedicated parameter can be used for comparator:
     * <pre>
     *     {@code @[if_date(comparator='>=', date='18/07/2026')]}
     * </pre>
     *
     * @param text the text where to extract the comparator
     * @return the comparator if found, otherwise {@link Optional#empty()}
     */
    public static Optional<Comparator> find(String text) {
        if (text.isEmpty()) return Optional.empty();
        char[] textArray = text.toCharArray();

        for (Comparator comparator : values()) {
            char[] sequence = comparator.symbol().toCharArray();
            if (Search.matches(textArray, sequence, 0)) return Optional.of(comparator);
        }

        return Optional.empty();
    }
}
