package com.github.jsf.dynamic_placeholders.phases.resolution.parser;

import com.github.jsf.dynamic_placeholders.components.Component;
import com.github.jsf.dynamic_placeholders.phases.resolution.lexer.tokens.Token;
import com.github.jsf.dynamic_placeholders.phases.resolution.lexer.tokens.TokenType;
import com.github.jsf.dynamic_placeholders.phases.resolution.lexer.tokens.Tokens;
import com.github.jsf.dynamic_placeholders.phases.resolution.scanner.beans.DPDelimiterSet;
import com.github.utilities.validators.Preconditions;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class Parser<C extends Component<?>> {
    private final Tokens tokens;
    private final DPDelimiterSet delimiters;
    private int index;

    protected Parser(Tokens tokens, DPDelimiterSet delimiters) {
        this.tokens = Preconditions.parameterNotNull(tokens, "tokens");
        this.delimiters = Preconditions.parameterNotNull(delimiters, "delimiters");
        this.index = 0;
    }

    public DPDelimiterSet delimiters() {
        return delimiters;
    }

    public Tokens tokens() {
        return tokens;
    }

    public Token prevToken() {
        if (!hasPrev()) return null;
        return tokens.at(index - 1);
    }

    public Token nextToken() {
        if (!hasNext()) return null;
        return tokens.at(index++);
    }

    public Optional<Token> nextTokenAs(TokenType type) {
        Token token = nextToken();
        if (token == null ||
                !token.type().equals(type))
            return Optional.empty();
        else return Optional.of(token);
    }

    public <E extends Throwable> Token nextTokenAs(TokenType type, Function<Token, E> differentType) throws E {
        return nextTokenAs(type, differentType, () -> {
            throw new UnexpectedTokenException("Expected token of type " + type + " but all tokens have been consumed.");
        });
    }

    public <E extends Throwable> Token nextTokenAs(TokenType type, Function<Token, E> differentType, Supplier<E> nullError) throws E {
        Token token = nextToken();
        if (token == null) {
            throw nullError.get();
        } else if (!token.type().equals(type)) {
            throw differentType.apply(token);
        } else return token;
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
