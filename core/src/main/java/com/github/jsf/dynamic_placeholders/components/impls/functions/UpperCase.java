package com.github.jsf.dynamic_placeholders.components.impls.functions;

import com.github.jsf.dynamic_placeholders.components.ComponentsInfo;
import com.github.jsf.dynamic_placeholders.names.UName;

public class UpperCase extends Function {
    public static final UName ID = new UName("upper_case", "upper", "uppercase");

    public UpperCase(ComponentsInfo infos) {
        super(ID, infos);
    }

    @Override
    public String evaluate(String actionResult) {
        return actionResult == null ? "" : actionResult.toUpperCase();
    }
}