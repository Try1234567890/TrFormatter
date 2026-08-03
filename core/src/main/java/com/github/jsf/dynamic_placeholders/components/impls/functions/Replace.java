package com.github.jsf.dynamic_placeholders.components.impls.functions;

import com.github.jsf.dynamic_placeholders.components.ComponentsInfo;
import com.github.jsf.dynamic_placeholders.names.UName;

public class Replace extends Function {
    public static final UName ID = new UName("replace");
    public static final UName TARGET = new UName("target", "from", "t");
    public static final UName REPLACEMENT = new UName("replacement", "to", "r");

    public Replace(ComponentsInfo infos) {
        super(ID, infos);
    }

    @Override
    public String evaluate(String actionResult) {
        if (actionResult == null) return "";
        String target = as(TARGET, String.class).orElseThrow(() ->
                new IllegalArgumentException("The parameter " + TARGET + " is required for \"" + ID + "\" function"));
        String replacement = as(REPLACEMENT, String.class).orElse("");
        return actionResult.replace(target, replacement);
    }
}