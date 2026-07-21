package com.github.jsf.dynamic_placeholders.components.impls.conditions.compare;

import com.github.jsf.dynamic_placeholders.components.Parameter;
import com.github.jsf.dynamic_placeholders.names.UName;

import java.util.List;
import java.util.Optional;

public interface Comparable {

    UName COMPARATOR = new UName("comparator", "comp");

    List<Parameter<?>> parameters();

    default Optional<Comparator> comparator() {
        try {
            return parameters()
                    .stream()
                    .filter(parameter -> COMPARATOR.is(parameter.name()))
                    .map(parameter -> parameter.as(String.class).orElse(""))
                    .map(Comparator::matches)
                    .findFirst()
                    .orElse(Optional.empty());
        } catch (Exception _) {
            return Optional.empty();
        }
    }
}
