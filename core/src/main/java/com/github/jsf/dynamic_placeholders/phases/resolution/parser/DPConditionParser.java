package com.github.jsf.dynamic_placeholders.phases.resolution.parser;

import com.github.jsf.dynamic_placeholders.components.impls.conditions.Condition;
import com.github.jsf.dynamic_placeholders.phases.resolution.lexer.tokens.TokenType;
import com.github.jsf.dynamic_placeholders.phases.resolution.lexer.tokens.Tokens;
import com.github.jsf.dynamic_placeholders.phases.resolution.scanner.beans.DPDelimiterSet;
import com.github.jsf.dynamic_placeholders.registries.Conditions;

public class DPConditionParser extends DPComponentParser<Condition> {

    public DPConditionParser(Tokens tokens, DPDelimiterSet delimiters) {
        super(tokens, delimiters, TokenType.OPEN_CONDITION, TokenType.CLOSE_CONDITION, Conditions.getInstance());
    }
}
