package com.github.jsf.dynamic_placeholders.registries;

import com.github.jsf.dynamic_placeholders.components.ComponentsInfo;
import com.github.jsf.dynamic_placeholders.components.impls.conditions.Condition;
import com.github.jsf.dynamic_placeholders.components.impls.conditions.IfDate;
import com.github.jsf.dynamic_placeholders.names.UName;
import com.github.utilities.validators.Preconditions;

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

    public static Conditions newCondition(UName name, Function<ComponentsInfo, Condition> condition) {
        Preconditions.parameterNotNull(condition, "condition");
        getInstance().register(name, condition);
        return getInstance();
    }

    public static Optional<Function<ComponentsInfo, Condition>> getCondition(UName name) {
        return getInstance().retrieve(Preconditions.parameterNotNull(name, "name"));
    }

    public static Optional<Function<ComponentsInfo, Condition>> getCondition(String name) {
        Preconditions.parameterNotNull(name, "name");

        for (Map.Entry<UName, Function<ComponentsInfo, Condition>> entry : getInstance().entries()) {
            UName actionName = entry.getKey();

            if (actionName.is(name)) return Optional.ofNullable(entry.getValue());
        }

        return Optional.empty();
    }
}