package com.github.jsf.dynamic_placeholders.components.impls.functions;

import com.github.jsf.dynamic_placeholders.components.Component;
import com.github.jsf.dynamic_placeholders.components.ComponentsInfo;
import com.github.jsf.dynamic_placeholders.names.UName;

public abstract class Function extends Component<String> {
    private String actionResult;

    protected Function(UName identifier, ComponentsInfo infos) {
        super(identifier, infos);
    }

    public Function withActionResult(String actionResult) {
        this.actionResult = actionResult;
        return this;
    }

    @Override
    public String evaluate() {
        return evaluate(actionResult);
    }

    public abstract String evaluate(String actionResult);
}
