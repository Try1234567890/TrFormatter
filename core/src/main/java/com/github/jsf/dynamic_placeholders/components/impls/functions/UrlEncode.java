package com.github.jsf.dynamic_placeholders.components.impls.functions;

import com.github.jsf.dynamic_placeholders.components.ComponentsInfo;
import com.github.jsf.dynamic_placeholders.names.UName;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class UrlEncode extends Function {
    public static final UName ID = new UName("url_encode", "urlencode");

    public UrlEncode(ComponentsInfo infos) {
        super(ID, infos);
    }

    @Override
    public String evaluate(String actionResult) {
        if (actionResult == null) return "";
        return URLEncoder.encode(actionResult, StandardCharsets.UTF_8);
    }
}