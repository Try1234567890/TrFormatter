package com.github.jsf.dynamic_placeholders.phases.resolution.parser;

import com.github.jsf.dynamic_placeholders.components.Component;
import com.github.jsf.dynamic_placeholders.phases.resolution.lexer.tokens.Token;
import com.github.jsf.dynamic_placeholders.phases.resolution.lexer.tokens.TokenType;
import com.github.jsf.dynamic_placeholders.phases.resolution.scanner.beans.DPDelimiterSet;
import com.github.jsf.scanners.IllegalComponentException;
import com.github.utilities.validators.Preconditions;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public abstract class Parser<C extends Component<?>> {
    private final List<Token> tokens;
    private final DPDelimiterSet delimiters;
    private int index;

    protected Parser(List<Token> tokens, DPDelimiterSet delimiters) {
        this.tokens = Preconditions.parameterNotNull(tokens, "tokens");
        this.delimiters = Preconditions.parameterNotNull(delimiters, "delimiters");
        this.index = 0;
    }

    public DPDelimiterSet delimiters() {
        return delimiters;
    }

    public Token prevToken() {
        if (!hasPrev()) return null;
        return tokens.get(index - 1);
    }

    public Token nextToken() {
        if (!hasNext()) return null;
        return tokens.get(index++);
    }

    public Optional<Token> nextTokenAs(TokenType type) {
        Token token = nextToken();
        if (token == null || !token.type().equals(type))
            return Optional.empty();
        else return Optional.of(token);
    }

    public boolean hasNext() {
        return index < tokens.size();
    }

    public boolean hasPrev() {
        return index > 0;
    }

    public int index() {
        return index;
    }

    public void plusIndex(int pos) {
        this.index += pos;
    }

    public abstract C parse();

}
