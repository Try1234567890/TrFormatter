package com.github.jsf.dynamic_placeholders.phases.resolution.lexer;

import com.github.jsf.dynamic_placeholders.phases.resolution.lexer.tokens.Tokens;
import com.github.jsf.dynamic_placeholders.phases.resolution.scanner.beans.DPDelimiterSet;
import com.github.jsf.dynamic_placeholders.phases.resolution.scanner.beans.IndexedDPComponent;

public class DPLexer extends Lexer {

    public DPLexer(IndexedDPComponent component,
                   DPDelimiterSet set) {
        super(component, set.getPlaceholders(), set);
    }

    @Override
    public IndexedDPComponent component() {
        return (IndexedDPComponent) super.component();
    }

    @Override
    public Tokens tokenize() {
        tokens().newTokens(new DPActionLexer(component().action(), set()).tokenize());
        component().conditions().forEach(condition -> tokens().newTokens(new DPConditionLexer(condition, set()).tokenize()));
        component().functions().forEach(function -> tokens().newTokens(new DPFunctionLexer(function, set()).tokenize()));

        return tokens();
    }
}























