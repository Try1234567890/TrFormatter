package com.github.jsf.dynamic_placeholders.registries;

import com.github.jsf.dynamic_placeholders.components.ComponentsInfo;
import com.github.jsf.dynamic_placeholders.components.impls.actions.Action;
import com.github.jsf.dynamic_placeholders.components.impls.actions.Console;
import com.github.jsf.dynamic_placeholders.components.impls.actions.RandomNumber;
import com.github.jsf.dynamic_placeholders.components.impls.actions.ReadFile;
import com.github.jsf.dynamic_placeholders.components.impls.actions.datetime.NowDate;
import com.github.jsf.dynamic_placeholders.components.impls.actions.datetime.NowTime;
import com.github.jsf.dynamic_placeholders.components.impls.actions.envs.Env;
import com.github.jsf.dynamic_placeholders.components.impls.actions.envs.Property;
import com.github.jsf.dynamic_placeholders.names.UName;
import com.github.utilities.validators.Preconditions;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class Actions extends ComponentsRegistry<Action> {
    private Actions() {
        register(Console.ID, Console::new);
        register(ReadFile.ID, ReadFile::new);
        register(NowDate.ID, NowDate::new);
        register(NowTime.ID, NowTime::new);
        register(Env.ID, Env::new);
        register(Property.ID, Property::new);
        register(RandomNumber.ID, RandomNumber::new);
    }

    private record Holder() {
        private static final Actions INSTANCE = new Actions();
    }

    public static Actions getInstance() {
        return Holder.INSTANCE;
    }

    public static Actions newAction(UName name, Function<ComponentsInfo, Action> action) {
        Preconditions.parameterNotNull(action, "action");
        getInstance().register(name, action);
        return getInstance();
    }

    public static Optional<Function<ComponentsInfo, Action>> getAction(UName name) {
        return getInstance().retrieve(Preconditions.parameterNotNull(name, "name"));
    }

    public static Optional<Function<ComponentsInfo, Action>> getAction(String name) {
        Preconditions.parameterNotNull(name, "name");

        for (Map.Entry<UName, Function<ComponentsInfo, Action>> entry : getInstance().entries()) {
            UName actionName = entry.getKey();

            if (actionName.is(name)) return Optional.ofNullable(entry.getValue());
        }

        return Optional.empty();
    }
}