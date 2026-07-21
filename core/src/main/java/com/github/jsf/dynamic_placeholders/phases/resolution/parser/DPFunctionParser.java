package com.github.jsf.dynamic_placeholders.phases.resolution.parser;

import com.github.jsf.dynamic_placeholders.components.Function;
import com.github.jsf.dynamic_placeholders.phases.resolution.lexer.tokens.Token;
import com.github.jsf.dynamic_placeholders.phases.resolution.lexer.tokens.TokenType;
import com.github.jsf.dynamic_placeholders.phases.resolution.scanner.beans.DPDelimiterSet;
import com.github.jsf.dynamic_placeholders.registries.Functions;

import java.util.List;

public class DPFunctionParser extends DPComponentParser<Function> {

    protected DPFunctionParser(List<Token> tokens, DPDelimiterSet delimiters) {
        super(tokens, delimiters, TokenType.OPEN_FUNCTION, TokenType.CLOSE_FUNCTION, Functions.getInstance());
    }
}
