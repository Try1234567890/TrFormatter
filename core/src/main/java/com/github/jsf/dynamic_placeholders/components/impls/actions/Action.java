package com.github.jsf.dynamic_placeholders.components.impls.actions;

import com.github.jsf.dynamic_placeholders.components.Component;
import com.github.jsf.dynamic_placeholders.components.ComponentsInfo;
import com.github.jsf.dynamic_placeholders.names.UName;

public abstract class Action extends Component<String> {

    protected Action(UName identifier, ComponentsInfo infos) {
        super(identifier, infos);
    }

}
