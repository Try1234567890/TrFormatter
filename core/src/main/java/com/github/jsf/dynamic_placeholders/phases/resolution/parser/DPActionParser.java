package com.github.jsf.dynamic_placeholders.phases.resolution.parser;

import com.github.jsf.dynamic_placeholders.components.impls.actions.Action;
import com.github.jsf.dynamic_placeholders.phases.resolution.lexer.tokens.TokenType;
import com.github.jsf.dynamic_placeholders.phases.resolution.lexer.tokens.Tokens;
import com.github.jsf.dynamic_placeholders.phases.resolution.scanner.beans.DPDelimiterSet;
import com.github.jsf.dynamic_placeholders.registries.Actions;

public class DPActionParser extends DPComponentParser<Action> {

    public DPActionParser(Tokens tokens, DPDelimiterSet delimiters) {
        super(tokens, delimiters, TokenType.OPEN_ACTION, TokenType.CLOSE_ACTION, Actions.getInstance());
    }
}
