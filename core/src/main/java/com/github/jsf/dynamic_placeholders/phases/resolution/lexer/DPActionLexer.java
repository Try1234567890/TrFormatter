package com.github.jsf.dynamic_placeholders.phases.resolution.lexer;

import com.github.jsf.dynamic_placeholders.phases.resolution.lexer.tokens.TokenType;
import com.github.jsf.dynamic_placeholders.phases.resolution.scanner.beans.DPDelimiterSet;
import com.github.jsf.scanners.components.IndexedComponent;

public class DPActionLexer extends DPComponentLexer {

    public DPActionLexer(IndexedComponent component, DPDelimiterSet set) {
        super(component, set.getActions(), TokenType.OPEN_ACTION, TokenType.CLOSE_ACTION, set);
    }
}