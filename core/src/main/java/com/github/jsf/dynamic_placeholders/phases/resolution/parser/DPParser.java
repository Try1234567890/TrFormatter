package com.github.jsf.dynamic_placeholders.phases.resolution.parser;

import com.github.jsf.dynamic_placeholders.components.impls.actions.Action;
import com.github.jsf.dynamic_placeholders.components.impls.conditions.Condition;
import com.github.jsf.dynamic_placeholders.components.DynamicPlaceholder;
import com.github.jsf.dynamic_placeholders.components.impls.functions.Function;
import com.github.jsf.dynamic_placeholders.phases.resolution.lexer.tokens.Token;
import com.github.jsf.dynamic_placeholders.phases.resolution.lexer.tokens.TokenType;
import com.github.jsf.dynamic_placeholders.phases.resolution.lexer.tokens.Tokens;
import com.github.jsf.dynamic_placeholders.phases.resolution.scanner.beans.DPDelimiterSet;
import com.github.jsf.scanners.IllegalComponentException;
import com.github.jsf.scanners.beans.Range;

import java.util.ArrayList;
import java.util.List;

public class DPParser extends Parser<DynamicPlaceholder> {

    public DPParser(Tokens tokens, DPDelimiterSet delimiters) {
        super(tokens, delimiters);
    }

    @Override
    public DynamicPlaceholder parse() {
        DynPLBuilder builder = new DynPLBuilder().withRange(tokens().range());

        while (hasNext()) {
            Token token = nextToken();
            if (token.type() == TokenType.CLOSE_PLACEHOLDER) break;

            if (token.type() == TokenType.OPEN_ACTION) {
                Tokens tokens = getUntil(TokenType.CLOSE_ACTION);
                builder.withAction(new DPActionParser(tokens, delimiters()).parse());
            } else if (token.type() == TokenType.OPEN_CONDITION) {
                Tokens tokens = getUntil(TokenType.CLOSE_CONDITION);
                builder.newCondition(new DPConditionParser(tokens, delimiters()).parse());
            } else if (token.type() == TokenType.OPEN_FUNCTION) {
                Tokens tokens = getUntil(TokenType.CLOSE_FUNCTION);
                builder.newFunction(new DPFunctionParser(tokens, delimiters()).parse());
            }
        }

        return builder.build();
    }

    private Tokens getUntil(TokenType type) {
        int start = index();
        List<Token> tokens = new ArrayList<>();

        tokens.add(prevToken()); // Should not be possible that doesn't have a previous token

        Token curr;
        while ((curr = nextToken()) != null
                && !type.equals(curr.type())) {
            tokens.add(curr);
        }

        TokenType tokenType = (curr == null ? null : curr.type());
        if (tokenType == null
                || !tokenType.equals(type)) {
            throw new UnexpectedTokenException("An unexpected token is found! Expected: " + type + ", found: " + tokenType);
        }

        tokens.add(curr);
        return new Tokens(tokens, new Range(start, index()));
    }

    private static class DynPLBuilder {
        private Action action;
        private Range range;
        private final List<Condition> conditions = new ArrayList<>();
        private final List<Function> functions = new ArrayList<>();

        private DynPLBuilder() {
        }

        private DynPLBuilder withRange(Range range) {
            this.range = range;
            return this;
        }

        private void withAction(Action action) {
            if (this.action != null) throw new IllegalComponentException("Cannot have more than one action");
            this.action = action;
        }

        private void newCondition(Condition condition) {
            this.conditions.add(condition);
        }

        private void newFunction(Function function) {
            this.functions.add(function);
        }

        private DynamicPlaceholder build() {
            if (this.action == null) throw new IllegalComponentException("Cannot have no action");
            if (this.range == null) throw new IllegalComponentException("Cannot have no range");

            return new DynamicPlaceholder(this.action, this.conditions, this.functions, this.range);
        }
    }
}









