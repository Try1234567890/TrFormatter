package com.github.jsf.dynamic_placeholders.phases.resolution.lexer;

import com.github.jsf.dynamic_placeholders.phases.resolution.lexer.tokens.Token;
import com.github.jsf.dynamic_placeholders.phases.resolution.lexer.tokens.TokenType;
import com.github.jsf.dynamic_placeholders.phases.resolution.scanner.beans.DPDelimiterSet;
import com.github.jsf.scanners.IllegalComponentException;
import com.github.jsf.scanners.components.IndexedComponent;
import com.github.jsf.scanners.delimiters.Delimiter;
import com.github.jsf.text.Text;
import com.github.utilities.validators.Preconditions;

import java.util.ArrayList;
import java.util.List;

public abstract class Lexer {
    private final IndexedComponent component;
    private final Text text;
    private final DPDelimiterSet set;
    private final Delimiter delimiter;
    private final List<Token> tokens = new ArrayList<>();
    private int cursor;

    public Lexer(IndexedComponent component,
                 Delimiter delimiter,
                 DPDelimiterSet set) {
        this.component = Preconditions.parameterNotNull(component, "component");
        this.text = component.getComponent();
        this.delimiter = Preconditions.parameterNotNull(delimiter, "delimiter");
        this.set = Preconditions.parameterNotNull(set, "set");
    }

    public IndexedComponent component() {
        return component;
    }

    public Text text() {
        return text;
    }

    public DPDelimiterSet set() {
        return set;
    }

    public Delimiter delimiter() {
        return delimiter;
    }

    public List<Token> tokens() {
        return tokens;
    }

    public int cursor() {
        return cursor;
    }

    protected void cursor(int i) {
        cursor = i;
    }

    protected void addCursor(int i) {
        cursor += i;
    }

    public abstract List<Token> tokenize();

    protected void consumeExpected(String expected, TokenType type) {
        if (match(expected)) {
            tokens.add(new Token(type, expected));
            cursor += expected.length();
        } else {
            throw new IllegalComponentException("Syntax Error: '" + expected + "' expected at index " + cursor + ". Found: " +
                    text.truncate(cursor, Math.min(cursor + 5, text.length()), "...").quoteWithSingle());
        }
    }

    protected boolean match(String expected) {
        return text.startsWith(expected, cursor);
    }

    protected String readUntil(String delimiter, Delimiter... toExcludeIndexesSearch) {
        int index = text.indexOfFromNonBetween(delimiter, cursor, toExcludeIndexesSearch);
        if (index == -1) {
            throw new IllegalComponentException("Syntax error: Delimiter '" + delimiter + "' not found before index " + cursor);
        }

        Text result = text.subtext(cursor, index);
        cursor = index;
        return result.toString();
    }

    protected String readUntilAny(String delimiter1, String delimiter2, Delimiter... toExcludeIndexesSearch) {
        int index1 = text.indexOfFromNonBetween(delimiter1, cursor, toExcludeIndexesSearch);
        int index2 = text.indexOfFromNonBetween(delimiter2, cursor, toExcludeIndexesSearch);

        if (index1 == -1) index1 = Integer.MAX_VALUE;
        if (index2 == -1) index2 = Integer.MAX_VALUE;

        int minIndex = Math.min(index1, index2);

        if (minIndex == Integer.MAX_VALUE) {
            throw new IllegalComponentException("Syntax error: No one of the expected delimiters '" + delimiter1 + "' or '" + delimiter2 + "'" +
                    " are found before index: " + cursor + ". Found: " +
                    text.truncate(cursor, Math.min(cursor + 5, text.length()), "...").quoteWithSingle());
        }

        Text result = text.subtext(cursor, minIndex);
        cursor = minIndex;
        return result.toString();
    }
}























