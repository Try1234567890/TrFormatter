package com.github.jsf.dynamic_placeholders.components;

import com.github.jsf.dynamic_placeholders.names.UName;

import java.util.List;

public abstract class Condition extends Component<Boolean> {
    protected Condition(UName identifier, List<Parameter<?>> parameters) {
        super(identifier, parameters);
    }
}
