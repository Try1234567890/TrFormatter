package com.github.jsf.dynamic_placeholders.phases.resolution.lexer;

import com.github.jsf.dynamic_placeholders.phases.resolution.lexer.tokens.TokenType;
import com.github.jsf.dynamic_placeholders.phases.resolution.scanner.beans.DPDelimiterSet;
import com.github.jsf.scanners.beans.IndexedComponent;

public class DPConditionLexer extends DPComponentLexer {


    public DPConditionLexer(IndexedComponent component, DPDelimiterSet set) {
        super(component, set.getConditions(), TokenType.OPEN_CONDITION, TokenType.CLOSE_CONDITION, set);
    }
}
