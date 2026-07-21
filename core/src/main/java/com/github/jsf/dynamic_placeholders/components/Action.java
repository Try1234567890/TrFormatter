package com.github.jsf.dynamic_placeholders.components;

import com.github.jsf.dynamic_placeholders.names.UName;

import java.util.List;

public abstract class Action extends Component<String> {

    protected Action(UName identifier, List<Parameter<?>> parameters) {
        super(identifier, parameters);
    }

}
