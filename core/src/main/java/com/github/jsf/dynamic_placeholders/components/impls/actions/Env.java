package com.github.jsf.dynamic_placeholders.components.impls.actions;

import com.github.jsf.dynamic_placeholders.components.ComponentsInfo;
import com.github.jsf.dynamic_placeholders.names.UName;

public class Env extends Action {
    public static final UName ID = new UName("env");
    public static final UName VARIABLE = new UName("variable", "var", "v");

    public Env(ComponentsInfo infos) {
        super(ID, infos);
    }

    @Override
    public String evaluate() {
        String var = as(VARIABLE, String.class).orElseThrow(() -> new IllegalArgumentException("The parameter " + VARIABLE + " is needed for \"" + ID + "\" action"));
        String env = System.getenv(var);
        return env == null ? "" : env;
    }
}