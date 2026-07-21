package com.github.jsf.dynamic_placeholders.registries;

import com.github.jsf.dynamic_placeholders.components.Action;
import com.github.jsf.dynamic_placeholders.components.Parameter;
import com.github.jsf.dynamic_placeholders.components.impls.actions.console.Console;
import com.github.jsf.dynamic_placeholders.names.UName;
import com.github.utilities.validators.Preconditions;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class Actions extends ComponentsRegistry<Action> {
    private Actions() {
        register(Console.ID, Console::new);

    }

    private record Holder() {
        private static final Actions INSTANCE = new Actions();
    }

    public static Actions getInstance() {
        return Holder.INSTANCE;
    }

    public static Actions newAction(UName name, Function<List<Parameter<?>>, Action> action) {
        Preconditions.parameterNotNull(action, "action");
        getInstance().register(name, action);
        return getInstance();
    }

    public static Optional<Function<List<Parameter<?>>, Action>> getAction(UName name) {
        return getInstance().retrieve(Preconditions.parameterNotNull(name, "name"));
    }

    public static Optional<Function<List<Parameter<?>>, Action>> getAction(String name) {
        Preconditions.parameterNotNull(name, "name");

        for (Map.Entry<UName, Function<List<Parameter<?>>, Action>> entry : getInstance().entries()) {
            UName actionName = entry.getKey();

            if (actionName.is(name)) return Optional.ofNullable(entry.getValue());
        }

        return Optional.empty();
    }
}