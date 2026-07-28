package com.github.jsf.dynamic_placeholders.components;

import com.github.jsf.dynamic_placeholders.components.impls.actions.Action;
import com.github.jsf.dynamic_placeholders.components.impls.conditions.Condition;
import com.github.jsf.dynamic_placeholders.components.impls.functions.Function;
import com.github.jsf.scanners.beans.Range;
import com.github.utilities.validators.Preconditions;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class DynamicPlaceholder extends Component<String> {
    private final Action action;
    private final List<Condition> conditions;
    private final List<Function> functions;
    private final AtomicReference<String> result = new AtomicReference<>(null);

    public DynamicPlaceholder(Action action, List<Condition> conditions, List<Function> functions, Range range) {
        super(action.name(), new ComponentsInfo(Collections.emptyList(), range));
        this.action = Preconditions.simpleParameterNotNull(action, "action");
        this.conditions = Preconditions.simpleParameterNotNull(conditions, "conditions");
        this.functions = Preconditions.simpleParameterNotNull(functions, "functions");
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

    public String getResult() {
        if (!isResolved()) throw new IllegalArgumentException("Dynamic placeholder " + this + " not resolved yet!");
        return result.get();
    }

    private boolean isResolved() {
        return result.get() != null;
    }

    @Override
    public String evaluate() {
        if (result.get() != null) {
            return result.get();
        }

        for (Condition condition : conditions) {
            if (!condition.evaluate()) {
                return "";
            }
        }

        String actionRes = action.evaluate();
        for (Function function : functions) {
            actionRes = function
                    .withActionResult(actionRes)
                    .evaluate();
        }

        result.set(actionRes);
        return actionRes;
    }

    @Override
    public String toString() {
        return "DynamicPlaceholder[Action: " + action + ", Conditions: " + conditions + ", Functions: " + functions + ']';
    }
}


























