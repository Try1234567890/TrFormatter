package com.github.jsf.dynamic_placeholders.phases.resolution.lexer;

import com.github.jsf.dynamic_placeholders.phases.resolution.lexer.tokens.TokenType;
import com.github.jsf.dynamic_placeholders.phases.resolution.scanner.beans.DPDelimiterSet;
import com.github.jsf.scanners.components.IndexedComponent;

public class DPFunctionLexer extends DPComponentLexer {


    public DPFunctionLexer(IndexedComponent component, DPDelimiterSet set) {
        super(component, set.getFunctions(), TokenType.OPEN_FUNCTION, TokenType.CLOSE_FUNCTION, set);
    }

}
