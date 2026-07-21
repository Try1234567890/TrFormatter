package com.github.jsf.dynamic_placeholders.registries;

import com.github.jsf.dynamic_placeholders.components.Function;
import com.github.jsf.dynamic_placeholders.components.Parameter;
import com.github.jsf.dynamic_placeholders.components.impls.functions.Truncate;
import com.github.jsf.dynamic_placeholders.names.UName;
import com.github.utilities.validators.Preconditions;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Functions extends ComponentsRegistry<Function> {
    private Functions() {
        register(Truncate.ID, Truncate::new);
    }

    private record Holder() {
        private static final Functions INSTANCE = new Functions();
    }

    public static Functions getInstance() {
        return Holder.INSTANCE;
    }

    public static Functions newCondition(UName name, java.util.function.Function<List<Parameter<?>>, Function> function) {
        Preconditions.parameterNotNull(function, "function");
        getInstance().register(name, function);
        return getInstance();
    }

    public static Optional<java.util.function.Function<List<Parameter<?>>, Function>> getFunction(UName name) {
        return getInstance().retrieve(Preconditions.parameterNotNull(name, "name"));
    }

    public static Optional<java.util.function.Function<List<Parameter<?>>, Function>> getFunction(String name) {
        Preconditions.parameterNotNull(name, "name");

        for (Map.Entry<UName, java.util.function.Function<List<Parameter<?>>, Function>> entry : getInstance().entries()) {
            UName actionName = entry.getKey();

            if (actionName.is(name)) return Optional.ofNullable(entry.getValue());
        }

        return Optional.empty();
    }
}