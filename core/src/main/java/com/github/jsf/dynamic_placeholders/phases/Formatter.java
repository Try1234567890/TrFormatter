package com.github.jsf.dynamic_placeholders.phases;

import com.github.jsf.dynamic_placeholders.components.DynamicPlaceholder;
import com.github.jsf.dynamic_placeholders.phases.resolution.lexer.DPLexer;
import com.github.jsf.dynamic_placeholders.phases.resolution.lexer.tokens.Token;
import com.github.jsf.dynamic_placeholders.phases.resolution.parser.DPParser;
import com.github.jsf.dynamic_placeholders.phases.resolution.scanner.DPScanner;
import com.github.jsf.dynamic_placeholders.phases.resolution.scanner.beans.DPDelimiterSet;
import com.github.jsf.dynamic_placeholders.phases.resolution.scanner.beans.IndexedDPComponent;
import com.github.jsf.text.Text;
import com.github.utilities.validators.Preconditions;

import java.util.ArrayList;
import java.util.List;

public class Formatter {
    private final Text text;
    private final DPDelimiterSet delimiters;
    private List<IndexedDPComponent> components;
    private List<List<Token>> tokens;
    private List<DynamicPlaceholder> placeholders;
    private List<String> results;

    public Formatter(Text text, DPDelimiterSet delimiters) {
        this.text = Preconditions.parameterNotNull(text, "text");
        this.delimiters = Preconditions.parameterNotNull(delimiters, "delimiters");
    }

    public Formatter(Text text) {
        this(text, DPDelimiterSet.DEFAULT);
    }

    public Text text() {
        return text;
    }

    public DPDelimiterSet delimiters() {
        return delimiters;
    }

    public List<IndexedDPComponent> components() {
        return components;
    }

    public List<List<Token>> tokens() {
        return tokens;
    }

    public List<DynamicPlaceholder> placeholders() {
        return placeholders;
    }

    public List<String> results() {
        return results;
    }

    public Text format() {
        this.components = new DPScanner(text, delimiters).scanAll();

        if (components.isEmpty()) {
            // No dynamic placeholders found.
            return text;
        }

        this.tokens = tokenize(components);
        this.placeholders = parse(tokens);
        this.results = resolve(placeholders);

        return new DPInterpolator(text, components, results).interpolate();
    }

    private List<String> resolve(List<DynamicPlaceholder> placeholders) {
        List<String> results = new ArrayList<>();

        for (DynamicPlaceholder placeholder : placeholders) {
            String result = placeholder.evaluate();
            results.add(result);
        }

        return results;
    }

    private List<DynamicPlaceholder> parse(List<List<Token>> tokens) {
        List<DynamicPlaceholder> placeholders = new ArrayList<>();

        for (List<Token> token : tokens) {
            DynamicPlaceholder placeholder = new DPParser(token, delimiters).parse();
            placeholders.add(placeholder);
        }

        return placeholders;
    }

    private List<List<Token>> tokenize(List<IndexedDPComponent> components) {
        List<List<Token>> result = new ArrayList<>();

        for (IndexedDPComponent component : components) {
            List<Token> tokens = new DPLexer(component, delimiters).tokenize();
            result.add(tokens);
        }

        return result;
    }
}























