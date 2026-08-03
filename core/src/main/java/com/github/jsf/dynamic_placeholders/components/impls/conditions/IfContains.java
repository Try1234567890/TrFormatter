package com.github.jsf.dynamic_placeholders.components.impls.conditions;

import com.github.jsf.dynamic_placeholders.components.ComponentsInfo;
import com.github.jsf.dynamic_placeholders.names.UName;
import com.github.jsf.scanners.IllegalComponentException;

public class IfContains extends Condition {
    public static final UName ID = new UName("if_contains");
    public static final UName TEXT = new UName("text", "t");
    public static final UName SUBSTRING = new UName("substring", "sub", "s");
    public static final UName IGNORE_CASE = new UName("ignore_case", "ic");

    public IfContains(ComponentsInfo infos) {
        super(ID, infos);
    }

    @Override
    public Boolean evaluate() {
        String text = as(TEXT, String.class).orElseThrow(() ->
                new IllegalComponentException("The " + TEXT + " parameter is required for " + ID + " condition."));
        String substring = as(SUBSTRING, String.class).orElseThrow(() ->
                new IllegalComponentException("The " + SUBSTRING + " parameter is required for " + ID + " condition."));
        boolean ignoreCase = as(IGNORE_CASE, Boolean.class).orElse(false);

        if (ignoreCase) {
            return text.toLowerCase().contains(substring.toLowerCase());
        }
        return text.contains(substring);
    }
}