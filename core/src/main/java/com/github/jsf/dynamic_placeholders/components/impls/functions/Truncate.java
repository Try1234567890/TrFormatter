package com.github.jsf.dynamic_placeholders.components.impls.functions;

import com.github.jsf.dynamic_placeholders.components.Function;
import com.github.jsf.dynamic_placeholders.components.Parameter;
import com.github.jsf.dynamic_placeholders.names.UName;

import java.util.List;

public class Truncate extends Function {
    public static final UName ID = new UName("truncate");
    public static final UName START = new UName("start");
    public static final UName END = new UName("end");

    public Truncate(List<Parameter<?>> parameters) {
        super(ID, parameters);
    }

    @Override
    public String evaluate(String actionResult) {
        int start = as(START, Number.class).orElse(0).intValue();
        int end = as(END, Number.class).orElse(actionResult.length()).intValue();

        if (start < 0) throw new IllegalArgumentException("Start cannot be negative");
        if (start > end) throw new IllegalArgumentException("Start cannot be greater than end");
        if (end > actionResult.length()) end = actionResult.length();

        return actionResult.substring(start, end);
    }
}
