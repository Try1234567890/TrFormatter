package com.github.jsf.dynamic_placeholders.registries;

import com.github.jsf.dynamic_placeholders.components.Condition;
import com.github.jsf.dynamic_placeholders.components.Parameter;
import com.github.jsf.dynamic_placeholders.components.impls.conditions.datetime.IfDate;
import com.github.jsf.dynamic_placeholders.names.UName;
import com.github.utilities.validators.Preconditions;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class Conditions extends ComponentsRegistry<Condition> {
    private Conditions() {
        register(IfDate.ID, IfDate::new);

    }

    private record Holder() {
        private static final Conditions INSTANCE = new Conditions();
    }

    public static Conditions getInstance() {
        return Holder.INSTANCE;
    }

    public static Conditions newCondition(UName name, Function<List<Parameter<?>>, Condition> condition) {
        Preconditions.parameterNotNull(condition, "condition");
        getInstance().register(name, condition);
        return getInstance();
    }

    public static Optional<Function<List<Parameter<?>>, Condition>> getCondition(UName name) {
        return getInstance().retrieve(Preconditions.parameterNotNull(name, "name"));
    }

    public static Optional<Function<List<Parameter<?>>, Condition>> getCondition(String name) {
        Preconditions.parameterNotNull(name, "name");

        for (Map.Entry<UName, Function<List<Parameter<?>>, Condition>> entry : getInstance().entries()) {
            UName actionName = entry.getKey();

            if (actionName.is(name)) return Optional.ofNullable(entry.getValue());
        }

        return Optional.empty();
    }
}