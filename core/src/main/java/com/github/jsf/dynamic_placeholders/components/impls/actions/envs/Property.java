package com.github.jsf.dynamic_placeholders.components.impls.actions.envs;

import com.github.jsf.dynamic_placeholders.components.ComponentsInfo;
import com.github.jsf.dynamic_placeholders.components.impls.actions.Action;
import com.github.jsf.dynamic_placeholders.names.UName;

public class Property extends Action {
    public static final UName ID = new UName("property");
    public static final UName VARIABLE = new UName("variable", "var", "v");
    public static final UName DEFAULT = new UName("default", "def");

    public Property(ComponentsInfo infos) {
        super(ID, infos);
    }

    @Override
    public String evaluate() {
        String var = as(VARIABLE, String.class).orElseThrow(() -> new IllegalArgumentException("The parameter " + VARIABLE + " is needed for \"" + ID + "\" action"));
        String def = as(DEFAULT, String.class).orElse("");
        String prop = System.getProperty(var, def);
        return prop == null ? "" : prop;
    }
}