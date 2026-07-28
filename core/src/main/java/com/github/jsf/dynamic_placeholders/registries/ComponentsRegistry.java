package com.github.jsf.dynamic_placeholders.registries;

import com.github.jsf.dynamic_placeholders.components.Component;
import com.github.jsf.dynamic_placeholders.names.UName;
import com.github.jsf.dynamic_placeholders.components.ComponentsInfo;
import com.github.utilities.registries.Registry;
import com.github.utilities.validators.Preconditions;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public abstract class ComponentsRegistry<C extends Component<?>> extends Registry<UName, Function<ComponentsInfo, C>> {

    public Optional<Function<ComponentsInfo, C>> getComponent(UName name) {
        return retrieve(Preconditions.parameterNotNull(name, "name"));
    }

    public Optional<Function<ComponentsInfo, C>> getComponent(String name) {
        Preconditions.parameterNotNull(name, "name");

        for (Map.Entry<UName, Function<ComponentsInfo, C>> entry : entries()) {
            UName actionName = entry.getKey();

            if (actionName.is(name)) return Optional.ofNullable(entry.getValue());
        }

        return Optional.empty();
    }
}
