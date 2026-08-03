package com.github.jsf.dynamic_placeholders.components.impls.functions;

import com.github.jsf.dynamic_placeholders.components.ComponentsInfo;
import com.github.jsf.dynamic_placeholders.names.UName;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExtract extends Function {
    public static final UName ID = new UName("regex_extract", "extract", "match");
    public static final UName PATTERN = new UName("pattern", "regex", "p");
    public static final UName GROUP = new UName("group", "g");

    public RegexExtract(ComponentsInfo infos) {
        super(ID, infos);
    }

    @Override
    public String evaluate(String actionResult) {
        if (actionResult == null) return "";
        String regex = as(PATTERN, String.class).orElseThrow(() ->
                new IllegalArgumentException("The parameter " + PATTERN + " is required for \"" + ID + "\" function"));
        int group = as(GROUP, Number.class).orElse(0).intValue();

        Matcher matcher = Pattern.compile(regex).matcher(actionResult);
        if (matcher.find()) {
            return matcher.group(group);
        }
        return "";
    }
}