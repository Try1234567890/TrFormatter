package com.github.jsf.dynamic_placeholders.phases.resolution.parser;

import com.github.jsf.dynamic_placeholders.components.Action;
import com.github.jsf.dynamic_placeholders.components.Condition;
import com.github.jsf.dynamic_placeholders.components.DynamicPlaceholder;
import com.github.jsf.dynamic_placeholders.components.Function;
import com.github.jsf.dynamic_placeholders.phases.resolution.lexer.tokens.Token;
import com.github.jsf.dynamic_placeholders.phases.resolution.lexer.tokens.TokenType;
import com.github.jsf.dynamic_placeholders.phases.resolution.scanner.beans.DPDelimiterSet;
import com.github.jsf.scanners.IllegalComponentException;

import java.util.ArrayList;
import java.util.List;

public class DPParser extends Parser<DynamicPlaceholder> {

    public DPParser(List<Token> tokens, DPDelimiterSet delimiters) {
        super(tokens, delimiters);
    }

    @Override
    public DynamicPlaceholder parse() {
        DynPLBuilder builder = new DynPLBuilder();

        while (hasNext()) {
            Token token = nextToken();
            if (token.type() == TokenType.CLOSE_PLACEHOLDER) break;

            if (token.type() == TokenType.OPEN_ACTION) {
                List<Token> tokens = getUntil(TokenType.CLOSE_ACTION);
                builder.withAction(new DPActionParser(tokens, delimiters()).parse());
            } else if (token.type() == TokenType.OPEN_CONDITION) {
                List<Token> tokens = getUntil(TokenType.CLOSE_CONDITION);
                builder.newCondition(new DPConditionParser(tokens, delimiters()).parse());
            } else if (token.type() == TokenType.OPEN_FUNCTION) {
                List<Token> tokens = getUntil(TokenType.CLOSE_FUNCTION);
                builder.newFunction(new DPFunctionParser(tokens, delimiters()).parse());
            }
        }

        return builder.build();
    }

    private List<Token> getUntil(TokenType type) {
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
        return tokens;
    }

    private static class DynPLBuilder {
        private Action action;
        private final List<Condition> conditions = new ArrayList<>();
        private final List<Function> functions = new ArrayList<>();


        private DynPLBuilder() {
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
            return new DynamicPlaceholder(this.action, this.conditions, this.functions);
        }
    }
}









