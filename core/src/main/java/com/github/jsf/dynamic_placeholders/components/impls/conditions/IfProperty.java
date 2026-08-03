package com.github.jsf.dynamic_placeholders.components.impls.conditions;

import com.github.jsf.dynamic_placeholders.components.ComponentsInfo;
import com.github.jsf.dynamic_placeholders.components.impls.conditions.Condition;
import com.github.jsf.dynamic_placeholders.names.UName;
import com.github.jsf.scanners.IllegalComponentException;

public class IfProperty extends Condition {
    public static final UName ID = new UName("if_property", "if_prop");
    public static final UName VARIABLE = new UName("variable", "var", "v");
    public static final UName VALUE = new UName("value", "val", "expected");

    public IfProperty(ComponentsInfo infos) {
        super(ID, infos);
    }

    @Override
    public Boolean evaluate() {
        String var = as(VARIABLE, String.class).orElseThrow(() ->
                new IllegalComponentException("The " + VARIABLE + " parameter is required for " + ID + " condition."));
        String propValue = System.getProperty(var);
        if (propValue == null) return false;

        return as(VALUE, String.class)
                .map(propValue::equals)
                .orElse(true);
    }
}