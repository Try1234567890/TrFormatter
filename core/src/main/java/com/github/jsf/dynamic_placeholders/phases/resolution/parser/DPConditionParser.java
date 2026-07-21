package com.github.jsf.dynamic_placeholders.phases.resolution.parser;

import com.github.jsf.dynamic_placeholders.components.Condition;
import com.github.jsf.dynamic_placeholders.phases.resolution.lexer.tokens.Token;
import com.github.jsf.dynamic_placeholders.phases.resolution.lexer.tokens.TokenType;
import com.github.jsf.dynamic_placeholders.phases.resolution.scanner.beans.DPDelimiterSet;
import com.github.jsf.dynamic_placeholders.registries.Conditions;

import java.util.List;

public class DPConditionParser extends DPComponentParser<Condition> {

    protected DPConditionParser(List<Token> tokens, DPDelimiterSet delimiters) {
        super(tokens, delimiters, TokenType.OPEN_CONDITION, TokenType.CLOSE_CONDITION, Conditions.getInstance());
    }
}
