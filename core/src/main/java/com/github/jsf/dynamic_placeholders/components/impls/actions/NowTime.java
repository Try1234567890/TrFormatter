package com.github.jsf.dynamic_placeholders.components.impls.actions;

import com.github.jsf.dynamic_placeholders.components.ComponentsInfo;
import com.github.jsf.dynamic_placeholders.names.UName;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class NowTime extends Action {
    public static final UName ID = new UName("now_time");
    public static final UName PATTERN = new UName("pattern", "format", "p");

    public NowTime(ComponentsInfo infos) {
        super(ID, infos);
    }

    @Override
    public String evaluate() {
        DateTimeFormatter formatter = as(PATTERN, String.class).map(DateTimeFormatter::ofPattern).orElse(DateTimeFormatter.ofPattern("HH:mm:ss"));
        return LocalTime.now().format(formatter);
    }
}
