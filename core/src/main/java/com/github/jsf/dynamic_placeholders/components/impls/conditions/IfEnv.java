package com.github.jsf.dynamic_placeholders.components.impls.conditions;

import com.github.jsf.dynamic_placeholders.components.ComponentsInfo;
import com.github.jsf.dynamic_placeholders.components.impls.conditions.Condition;
import com.github.jsf.dynamic_placeholders.names.UName;
import com.github.jsf.scanners.IllegalComponentException;

public class IfEnv extends Condition {
    public static final UName ID = new UName("if_env");
    public static final UName VARIABLE = new UName("variable", "var", "v");
    public static final UName VALUE = new UName("value", "val", "expected");

    public IfEnv(ComponentsInfo infos) {
        super(ID, infos);
    }

    @Override
    public Boolean evaluate() {
        String var = as(VARIABLE, String.class).orElseThrow(() ->
                new IllegalComponentException("The " + VARIABLE + " parameter is required for " + ID + " condition."));
        String envValue = System.getenv(var);
        if (envValue == null) return false;

        return as(VALUE, String.class)
                .map(envValue::equals)
                .orElse(true);
    }
}