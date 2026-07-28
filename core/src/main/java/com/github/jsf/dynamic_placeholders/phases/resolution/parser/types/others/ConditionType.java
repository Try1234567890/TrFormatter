package com.github.jsf.dynamic_placeholders.phases.resolution.parser.types.others;

import com.github.jsf.dynamic_placeholders.components.impls.conditions.Condition;
import com.github.jsf.dynamic_placeholders.phases.Formatter;
import com.github.jsf.dynamic_placeholders.phases.resolution.parser.types.ParameterType;
import com.github.jsf.dynamic_placeholders.phases.resolution.scanner.beans.DPDelimiterSet;
import com.github.jsf.text.Text;

import java.util.Optional;

public class ConditionType extends ParameterType<Boolean> {

    @Override
    protected Optional<Boolean> _is(Text str, DPDelimiterSet set) {
        Formatter formatter = new Formatter(str, set);
        if (formatter.matchesCondition()) {
            return formatter.asCondition()
                    .map(Condition::evaluate);
        }
        return Optional.empty();
    }

}
