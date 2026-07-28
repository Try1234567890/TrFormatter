package com.github.jsf.dynamic_placeholders.components.impls.actions;

import com.github.jsf.color.ansi.ANSI;
import com.github.jsf.color.ansi.ANSI24Bit;
import com.github.jsf.dynamic_placeholders.components.ComponentsInfo;
import com.github.jsf.dynamic_placeholders.names.UName;

public class Console extends Action {
    public static final UName ID = new UName("console");
    public static final UName MESSAGE = new UName("message", "msg", "m");
    public static final UName COLOR = new UName("color", "c");

    public Console(ComponentsInfo infos) {
        super(ID, infos);
    }

    @Override
    public String evaluate() {
        String message = as(MESSAGE, String.class).orElseThrow(() ->
                new IllegalArgumentException("The parameter " + MESSAGE + " is needed for \"" + ID + "\" action"));
        String color = hex();

        System.out.println(color + message + ANSI.RESET_TAG);
        return message;
    }

    private String hex() {
        return as(COLOR, String.class)
                .map(s -> ANSI24Bit.ofHex(s).getTextEscapeSequence())
                .orElse("");
    }

}
