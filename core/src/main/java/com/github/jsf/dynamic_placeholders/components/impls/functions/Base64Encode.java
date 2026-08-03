package com.github.jsf.dynamic_placeholders.components.impls.functions;

import com.github.jsf.dynamic_placeholders.components.ComponentsInfo;
import com.github.jsf.dynamic_placeholders.names.UName;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Encode extends Function {
    public static final UName ID = new UName("base64_encode", "base64", "b64_enc");

    public Base64Encode(ComponentsInfo infos) {
        super(ID, infos);
    }

    @Override
    public String evaluate(String actionResult) {
        if (actionResult == null) return "";
        return Base64.getEncoder().encodeToString(actionResult.getBytes(StandardCharsets.UTF_8));
    }
}