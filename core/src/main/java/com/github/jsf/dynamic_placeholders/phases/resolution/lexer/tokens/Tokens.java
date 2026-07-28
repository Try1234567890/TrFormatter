package com.github.jsf.dynamic_placeholders.phases.resolution.lexer.tokens;

import com.github.jsf.scanners.beans.Range;
import com.github.utilities.validators.Preconditions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class Tokens {
    private final List<Token> tokens;
    private final Range range;


    public Tokens(List<Token> tokens, Range range) {
        this.tokens = Preconditions.simpleParameterNotNull(tokens, "tokens");
        this.range = Preconditions.simpleParameterNotNull(range, "range");
    }
    public Tokens(Range range) {
        this(new ArrayList<>(), range);
    }

    public List<Token> tokens() {
        return new ArrayList<>(tokens);
    }

    public Token at(int index) {
        return tokens.get(index);
    }

    public int size() {
        return tokens.size();
    }

    public void newToken(Token token) {
        Preconditions.simpleParameterNotNull(token, "token");
        tokens.add(token);
    }

    public void newTokens(Collection<Token> tokens) {
        Preconditions.simpleParameterNotNull(tokens, "tokens");
        this.tokens.addAll(tokens);
    }

    public void newTokens(Tokens tokens) {
        Preconditions.simpleParameterNotNull(tokens, "tokens");
        this.tokens.addAll(tokens.tokens);
    }

    public void newTokens(Token... tokens) {
        Preconditions.simpleParameterNotNull(tokens, "tokens");
        this.tokens.addAll(List.of(tokens));
    }

    public Range range() {
        return range;
    }

    @Override
    public String toString() {
        return "Tokens{Tokens: " + tokens + ", Range: " + range + "}";
    }
}
