package com.github.jsf.dynamic_placeholders.phases.resolution.lexer;

import com.github.jsf.dynamic_placeholders.phases.resolution.lexer.tokens.Token;
import com.github.jsf.dynamic_placeholders.phases.resolution.scanner.beans.DPDelimiterSet;
import com.github.jsf.dynamic_placeholders.phases.resolution.scanner.beans.IndexedDPComponent;

import java.util.List;

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
    public List<Token> tokenize() {
        tokens().addAll(new DPActionLexer(component().action(), set()).tokenize());
        component().conditions().forEach(condition -> tokens().addAll(new DPConditionLexer(condition, set()).tokenize()));
        component().functions().forEach(function -> tokens().addAll(new DPFunctionLexer(function, set()).tokenize()));

        return tokens();
    }
}























