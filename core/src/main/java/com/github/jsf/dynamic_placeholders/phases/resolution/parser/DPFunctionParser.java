package com.github.jsf.dynamic_placeholders.phases.resolution.parser;

import com.github.jsf.dynamic_placeholders.components.impls.functions.Function;
import com.github.jsf.dynamic_placeholders.phases.resolution.lexer.tokens.TokenType;
import com.github.jsf.dynamic_placeholders.phases.resolution.lexer.tokens.Tokens;
import com.github.jsf.dynamic_placeholders.phases.resolution.scanner.beans.DPDelimiterSet;
import com.github.jsf.dynamic_placeholders.registries.Functions;

public class DPFunctionParser extends DPComponentParser<Function> {

    public DPFunctionParser(Tokens tokens, DPDelimiterSet delimiters) {
        super(tokens, delimiters, TokenType.OPEN_FUNCTION, TokenType.CLOSE_FUNCTION, Functions.getInstance());
    }
}
