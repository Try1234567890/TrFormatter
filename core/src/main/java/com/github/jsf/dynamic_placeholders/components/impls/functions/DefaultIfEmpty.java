package com.github.jsf.dynamic_placeholders.components.impls.functions;

import com.github.jsf.dynamic_placeholders.components.ComponentsInfo;
import com.github.jsf.dynamic_placeholders.names.UName;

public class DefaultIfEmpty extends Function {
    public static final UName ID = new UName("default_if_empty", "default", "def");
    public static final UName VALUE = new UName("value", "val", "v", "default");

    public DefaultIfEmpty(ComponentsInfo infos) {
        super(ID, infos);
    }

    @Override
    public String evaluate(String actionResult) {
        String defVal = as(VALUE, String.class).orElse("");
        if (actionResult == null || actionResult.isEmpty()) {
            return defVal;
        }
        return actionResult;
    }
}