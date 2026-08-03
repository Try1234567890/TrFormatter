package com.github.jsf.dynamic_placeholders.components.impls.conditions;

import com.github.jsf.dynamic_placeholders.components.ComponentsInfo;
import com.github.jsf.dynamic_placeholders.names.UName;
import com.github.jsf.scanners.IllegalComponentException;

public class IfMatches extends Condition {
    public static final UName ID = new UName("if_matches", "if_regex");
    public static final UName TEXT = new UName("text", "t");
    public static final UName PATTERN = new UName("pattern", "regex", "p");

    public IfMatches(ComponentsInfo infos) {
        super(ID, infos);
    }

    @Override
    public Boolean evaluate() {
        String text = as(TEXT, String.class).orElseThrow(() ->
                new IllegalComponentException("The " + TEXT + " parameter is required for " + ID + " condition."));
        String regex = as(PATTERN, String.class).orElseThrow(() ->
                new IllegalComponentException("The " + PATTERN + " parameter is required for " + ID + " condition."));
        return text.matches(regex);
    }
}