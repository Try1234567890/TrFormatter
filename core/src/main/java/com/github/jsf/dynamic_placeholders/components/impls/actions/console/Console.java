package com.github.jsf.dynamic_placeholders.components.impls.actions.console;

import com.github.jsf.color.ansi.ANSI;
import com.github.jsf.color.ansi.ANSI24Bit;
import com.github.jsf.dynamic_placeholders.components.Action;
import com.github.jsf.dynamic_placeholders.components.Parameter;
import com.github.jsf.dynamic_placeholders.names.UName;

import java.util.List;

public class Console extends Action {
    public static final UName ID = new UName("console");
    public static final UName MESSAGE = new UName("message", "msg", "m");
    public static final UName COLOR = new UName("color", "c");
    public static final UName REPLACEMENT = new UName("replacement", "replace", "r");

    public Console(List<Parameter<?>> parameters) {
        super(ID, parameters);
    }

    @Override
    public String evaluate() {
        String message = as(MESSAGE, String.class).orElseThrow(() -> new IllegalArgumentException("The parameter with ID " + MESSAGE + " is required for \"console\" action"));
        String color = hex();
        Output replacement = replacement();

        System.out.println(color + message + ANSI.RESET_TAG);
        return replacement == Output.NOTHING ? "" : message;
    }

    private String hex() {
        return as(COLOR, String.class)
                .map(s -> ANSI24Bit.ofHex(s).getTextEscapeSequence())
                .orElse("");
    }

    private Output replacement() {
        String replacement = as(REPLACEMENT, String.class).orElse("MESSAGE").trim().toUpperCase();
        try {
            return Output.valueOf(replacement);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid replacement value: \"" + replacement + "\". The valid values are: " + Output.allAsString(), e);
        }
    }

    public enum Output {

        MESSAGE("Replace with the message sent to console. (The parameter 'msg')"), NOTHING("Replace with an empty string. (The placeholder will be simply removed)");

        private final String desc;

        Output(String desc) {
            this.desc = desc;
        }

        public static String allAsString() {
            StringBuilder sb = new StringBuilder();

            for (Output replacement : values()) {
                sb.append(replacement.name())
                        .append(": ")
                        .append(replacement.desc)
                        .append(", ");
            }

            return sb.substring(0, sb.length() - 2);
        }
    }
}
