package com.github.jsf.dynamic_placeholders.components;

import com.github.jsf.dynamic_placeholders.names.UName;

import java.util.List;

public abstract class Function extends Component<String> {
    private String actionResult;

    protected Function(UName identifier, List<Parameter<?>> parameters) {
        super(identifier, parameters);
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
