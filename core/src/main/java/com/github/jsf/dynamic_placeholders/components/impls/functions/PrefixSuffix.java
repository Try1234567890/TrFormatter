package com.github.jsf.dynamic_placeholders.components.impls.functions;

import com.github.jsf.dynamic_placeholders.components.ComponentsInfo;
import com.github.jsf.dynamic_placeholders.names.UName;

public class PrefixSuffix extends Function {
    public static final UName ID = new UName("prefix_suffix", "wrap");
    public static final UName PREFIX = new UName("prefix", "p", "pre");
    public static final UName SUFFIX = new UName("suffix", "s", "suf");

    public PrefixSuffix(ComponentsInfo infos) {
        super(ID, infos);
    }

    @Override
    public String evaluate(String actionResult) {
        String prefix = as(PREFIX, String.class).orElse("");
        String suffix = as(SUFFIX, String.class).orElse("");
        return prefix + (actionResult == null ? "" : actionResult) + suffix;
    }
}