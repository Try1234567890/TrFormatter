package com.github.jsf.dynamic_placeholders.components;

import java.util.ArrayList;
import java.util.List;

public class DynamicPlaceholder extends Component<String> {
    private final Action action;
    private final List<Condition> conditions;
    private final List<Function> functions;

    public DynamicPlaceholder(Action action, List<Condition> conditions, List<Function> functions) {
        super(action.name(), new ArrayList<>());
        this.action = action;
        this.conditions = conditions;
        this.functions = functions;
    }

    public Action action() {
        return action;
    }

    public List<Condition> conditions() {
        return conditions;
    }

    public List<Function> functions() {
        return functions;
    }

    @Override
    public String evaluate() {
        for (Condition condition : conditions) {
            if (!condition.evaluate()) return "";
        }
        String actionRes = action.evaluate();

        for (Function function : functions) {
            actionRes = function
                    .withActionResult(actionRes)
                    .evaluate();
        }

        return actionRes;
    }

    @Override
    public String toString() {
        return "DynamicPlaceholder[Action: " + action + ", Conditions: " + conditions + ", Functions: " + functions + ']';
    }
}


























