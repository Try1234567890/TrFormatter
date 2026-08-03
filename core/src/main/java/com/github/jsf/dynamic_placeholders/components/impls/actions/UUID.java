package com.github.jsf.dynamic_placeholders.components.impls.actions;

import com.github.jsf.dynamic_placeholders.components.ComponentsInfo;
import com.github.jsf.dynamic_placeholders.names.UName;

public class UUID extends Action {
    public static final UName ID = new UName("uuid");
    public static final UName UPPERCASE = new UName("uppercase", "upper", "u");

    public UUID(ComponentsInfo infos) {
        super(ID, infos);
    }

    @Override
    public String evaluate() {
        String uuid = java.util.UUID.randomUUID().toString();
        boolean upper = as(UPPERCASE, Boolean.class).orElse(false);
        return upper ? uuid.toUpperCase() : uuid;
    }
}