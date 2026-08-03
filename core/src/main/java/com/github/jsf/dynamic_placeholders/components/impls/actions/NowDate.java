package com.github.jsf.dynamic_placeholders.components.impls.actions;

import com.github.jsf.dynamic_placeholders.components.ComponentsInfo;
import com.github.jsf.dynamic_placeholders.names.UName;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NowDate extends Action {
    public static final UName ID = new UName("now_date");
    public static final UName PATTERN = new UName("pattern", "format", "p");

    public NowDate(ComponentsInfo infos) {
        super(ID, infos);
    }

    @Override
    public String evaluate() {
        DateTimeFormatter formatter = as(PATTERN, String.class).map(DateTimeFormatter::ofPattern).orElse(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return LocalDate.now().format(formatter);
    }
}
