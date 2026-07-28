package com.github.jsf.dynamic_placeholders.components;

import com.github.jsf.dynamic_placeholders.names.UName;
import com.github.jsf.scanners.beans.Range;

import java.util.List;
import java.util.Optional;

public abstract class Component<R> {
    private final UName name;
    private final List<Parameter<?>> parameters;
    private final Range range;

    protected Component(UName name, ComponentsInfo infos) {
        this.name = name;
        this.parameters = infos.parameters();
        this.range = infos.range();
    }

    public UName name() {
        return name;
    }

    public List<Parameter<?>> parameters() {
        return parameters;
    }

    public Range range() {
        return range;
    }

    public int start() {
        return range.start();
    }

    public int end() {
        return range.end();
    }

    public Optional<Parameter<?>> getParameter(UName name) {
        for (Parameter<?> parameter : parameters) {
            String paramName = parameter.name();
            if (name.is(paramName)) return Optional.of(parameter);
        }
        return Optional.empty();
    }

    public Optional<Parameter<?>> getParameter(String name) {
        for (Parameter<?> parameter : parameters) {
            String paramName = parameter.name();
            if (paramName.equalsIgnoreCase(name)) return Optional.of(parameter);
        }
        return Optional.empty();
    }

    public Optional<Object> get(String name) {
        return getParameter(name)
                .map(Parameter::value);
    }

    public Optional<Object> get(UName name) {
        return getParameter(name)
                .map(Parameter::value);
    }

    public <T> Optional<T> as(String name, Class<T> type) {
        return getParameter(name)
                .map(Parameter::value)
                .filter(type::isInstance)
                .map(type::cast);
    }

    public <T> Optional<T> as(UName name, Class<T> type) {
        return getParameter(name)
                .map(Parameter::value)
                .filter(type::isInstance)
                .map(type::cast);
    }

    public abstract R evaluate();

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[Name: " + name + ", Params: " + parameters + ']';
    }
}






















