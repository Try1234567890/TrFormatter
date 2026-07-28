package com.github.jsf.dynamic_placeholders.components.impls.conditions;

import com.github.jsf.dynamic_placeholders.components.Component;
import com.github.jsf.dynamic_placeholders.components.ComponentsInfo;
import com.github.jsf.dynamic_placeholders.names.UName;

public abstract class Condition extends Component<Boolean> implements Comparable {
    protected Condition(UName identifier, ComponentsInfo infos) {
        super(identifier, infos);
    }
}
