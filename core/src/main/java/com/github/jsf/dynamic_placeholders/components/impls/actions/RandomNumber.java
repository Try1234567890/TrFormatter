package com.github.jsf.dynamic_placeholders.components.impls.actions;

import com.github.jsf.dynamic_placeholders.components.ComponentsInfo;
import com.github.jsf.dynamic_placeholders.names.UName;

import java.util.Optional;
import java.util.Random;

public class RandomNumber extends Action {
    public static final Random RANDOM = new Random();
    public static final UName ID = new UName("random_number");
    public static final UName MIN = new UName("minimum", "min");
    public static final UName MAX = new UName("maximum", "max");

    public RandomNumber(ComponentsInfo infos) {
        super(ID, infos);
    }

    @Override
    public String evaluate() {
        Optional<Double> min = as(MIN, Number.class).map(Number::doubleValue);
        Optional<Double> max = as(MAX, Number.class).map(Number::doubleValue);

        if (min.isPresent() && max.isPresent())
            return String.valueOf(RANDOM.nextDouble(min.get(), max.get()));
        else
            return min
                    .map(value -> String.valueOf(RANDOM.nextDouble(value)))
                    .orElseGet(() -> max
                            .map(aDouble -> String.valueOf(RANDOM.nextDouble(aDouble)))
                            .orElseGet(() -> String.valueOf(RANDOM.nextDouble())));
    }
}
