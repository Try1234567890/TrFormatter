package com.github.jsf.dynamic_placeholders.phases.resolution.parser;

import com.github.jsf.dynamic_placeholders.components.Action;
import com.github.jsf.dynamic_placeholders.phases.resolution.lexer.tokens.Token;
import com.github.jsf.dynamic_placeholders.phases.resolution.lexer.tokens.TokenType;
import com.github.jsf.dynamic_placeholders.phases.resolution.scanner.beans.DPDelimiterSet;
import com.github.jsf.dynamic_placeholders.registries.Actions;

import java.util.List;

public class DPActionParser extends DPComponentParser<Action> {

    protected DPActionParser(List<Token> tokens, DPDelimiterSet delimiters) {
        super(tokens, delimiters, TokenType.OPEN_ACTION, TokenType.CLOSE_ACTION, Actions.getInstance());
    }
}
