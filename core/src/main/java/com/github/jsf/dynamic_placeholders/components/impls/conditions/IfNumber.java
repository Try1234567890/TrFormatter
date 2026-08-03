package com.github.jsf.dynamic_placeholders.components.impls.conditions;

import com.github.jsf.dynamic_placeholders.components.ComponentsInfo;
import com.github.jsf.dynamic_placeholders.names.UName;
import com.github.jsf.scanners.IllegalComponentException;

public class IfNumber extends Condition {
    public static final UName ID = new UName("if_number", "if_num");
    public static final UName VALUE = new UName("value", "val", "v");
    public static final UName TARGET = new UName("target", "t");

    public IfNumber(ComponentsInfo infos) {
        super(ID, infos);
    }

    @Override
    public Boolean evaluate() {
        double value = as(VALUE, Number.class).map(Number::doubleValue).orElseThrow(() ->
                new IllegalComponentException("The " + VALUE + " parameter is required for " + ID + " condition."));
        double target = as(TARGET, Number.class).map(Number::doubleValue).orElse(0.0);
        
        Comparator comp = comparator().orElse(Comparator.EQUALS);
        return switch (comp) {
            case MINOR -> value < target;
            case MINOR_OR_EQUAL -> value <= target;
            case EQUALS -> Double.compare(value, target) == 0;
            case GREATER_OR_EQUAL -> value >= target;
            case GREATER -> value > target;
        };
    }
}