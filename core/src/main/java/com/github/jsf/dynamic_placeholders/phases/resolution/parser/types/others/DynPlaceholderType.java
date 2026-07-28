package com.github.jsf.dynamic_placeholders.phases.resolution.parser.types.others;

import com.github.jsf.dynamic_placeholders.components.DynamicPlaceholder;
import com.github.jsf.dynamic_placeholders.components.impls.functions.Function;
import com.github.jsf.dynamic_placeholders.phases.Formatter;
import com.github.jsf.dynamic_placeholders.phases.resolution.parser.types.ParameterType;
import com.github.jsf.dynamic_placeholders.phases.resolution.scanner.beans.DPDelimiterSet;
import com.github.jsf.text.Text;

import java.util.Optional;

public class DynPlaceholderType extends ParameterType<String> {

    @Override
    protected Optional<String> _is(Text str, DPDelimiterSet set) {
        Formatter formatter = new Formatter(str, set);
        if (formatter.matchesDynamicPlaceholder()) {
            return formatter.asDynamicPlaceholder()
                    .map(DynamicPlaceholder::evaluate);
        }
        return Optional.empty();
    }

}
