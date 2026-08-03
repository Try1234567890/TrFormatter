package com.github.jsf.dynamic_placeholders.components.impls.conditions;

import com.github.jsf.dynamic_placeholders.components.ComponentsInfo;
import com.github.jsf.dynamic_placeholders.components.impls.conditions.Comparator;
import com.github.jsf.dynamic_placeholders.components.impls.conditions.Condition;
import com.github.jsf.dynamic_placeholders.names.UName;
import com.github.jsf.scanners.IllegalComponentException;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class IfTime extends Condition {
    public static final UName ID = new UName("if_time");
    public static final UName TIME = new UName("time", "t");
    public static final UName PATTERN = new UName("pattern", "format", "p");

    public IfTime(ComponentsInfo infos) {
        super(ID, infos);
    }

    @Override
    public Boolean evaluate() {
        String timeStr = as(TIME, String.class).orElseThrow(() ->
                new IllegalComponentException("The " + TIME + " parameter is required for " + ID + " condition."));
        Comparator comp = comparator().orElseThrow(() ->
                new IllegalComponentException("The " + COMPARATOR + " parameter is required for " + ID + " condition."));
        
        DateTimeFormatter formatter = as(PATTERN, String.class)
                .map(DateTimeFormatter::ofPattern)
                .orElse(DateTimeFormatter.ofPattern("HH:mm:ss"));

        LocalTime targetTime;
        try {
            targetTime = LocalTime.parse(timeStr, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalComponentException("An error occurred while parsing the " + TIME + " parameter for " + ID + " condition.", e);
        }

        LocalTime now = LocalTime.now();
        return switch (comp) {
            case MINOR -> targetTime.isBefore(now);
            case MINOR_OR_EQUAL -> targetTime.isBefore(now) || targetTime.equals(now);
            case EQUALS -> targetTime.equals(now);
            case GREATER_OR_EQUAL -> targetTime.isAfter(now) || targetTime.equals(now);
            case GREATER -> targetTime.isAfter(now);
        };
    }
}