package com.github.jsf.dynamic_placeholders.components.impls.conditions.datetime;

import com.github.jsf.dynamic_placeholders.components.Condition;
import com.github.jsf.dynamic_placeholders.components.Parameter;
import com.github.jsf.dynamic_placeholders.components.impls.conditions.compare.Comparable;
import com.github.jsf.dynamic_placeholders.components.impls.conditions.compare.Comparator;
import com.github.jsf.dynamic_placeholders.names.UName;
import com.github.jsf.scanners.IllegalComponentException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class IfDate extends Condition implements Comparable {
    public static final UName ID = new UName("if_date");
    public static final UName DATE = new UName("date", "d");
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public IfDate(List<Parameter<?>> parameters) {
        super(ID, parameters);
    }

    @Override
    public Boolean evaluate() {
        String dateStr = as(DATE, String.class).orElseThrow(() -> new IllegalComponentException("The " + DATE + " parameter is required for " + ID + " condition."));
        Comparator comparator = comparator().orElseThrow(() -> new IllegalComponentException("The " + COMPARATOR + " parameter is required for " + ID + " condition."));
        LocalDate date = getDate(dateStr);
        return compare(comparator, date);
    }

    private boolean compare(Comparator comparator,
                            LocalDate date) {
        LocalDate now = LocalDate.now();
        return switch (comparator) {
            case MINOR -> date.isBefore(now);
            case MINOR_OR_EQUAL -> date.isBefore(now) || date.isEqual(now);
            case EQUALS -> date.isEqual(now);
            case GREATER_OR_EQUAL -> date.isAfter(now) || date.isEqual(now);
            case GREATER -> date.isAfter(now);
        };
    }

    private LocalDate getDate(String raw) {
        try {
            return LocalDate.parse(raw, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalComponentException("An error occurs while parsing the " + DATE + " parameter for " + ID + " condition. Please make sure that follow the format dd/MM/yyyy.");
        }

    }
}
















