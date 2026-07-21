package com.github.jsf.dynamic_placeholders.components;

import com.github.jsf.dynamic_placeholders.phases.resolution.parser.types.ParameterType;

import java.util.Optional;

public record Parameter<T>(String name, ParameterType<T> type, T value) {

    public <O_T> Optional<O_T> as(Class<O_T> type) {
        if (type.isInstance(value)) {
            return Optional.of(type.cast(value));
        } else {
            return type().as(value, type).or(Optional::empty);
        }
    }

}
