package com.github.jsf.dynamic_placeholders.phases.resolution.parser.types.others.colors;

import com.github.jsf.color.Color;
import com.github.jsf.dynamic_placeholders.phases.resolution.parser.types.ParameterType;

import java.util.Optional;

public abstract class ColorType extends ParameterType<Color> {

    @Override
    public <O_T> Optional<O_T> as(Color value, Class<? extends O_T> type) {
        if (String.class.isAssignableFrom(type)) {
            String string = value.toHex();
            return Optional.of((O_T) string);
        }
        return Optional.empty();
    }
}















