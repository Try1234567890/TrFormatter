package com.github.jsf.dynamic_placeholders.phases.resolution.parser.types;

import com.github.jsf.dynamic_placeholders.phases.resolution.scanner.beans.DPDelimiterSet;
import com.github.jsf.text.Text;

import java.util.Optional;

public class BooleanType extends ParameterType<Boolean> {
    @Override
    protected Optional<Boolean> _is(Text str, DPDelimiterSet set) {
        if (str.equalsIgnoreCase("true")
                || str.equalsIgnoreCase("false")) {
            boolean bool = str.asBoolean();
            return Optional.of(bool);
        }
        return Optional.empty();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <O_T> Optional<O_T> as(Boolean value, Class<? extends O_T> type) {
        if (Number.class.isAssignableFrom(type)) {
            Number number = (value ? 1 : 0);
            return Optional.of((O_T) number);
        }
        if (String.class.isAssignableFrom(type)) {
            String string = value.toString();
            return Optional.of((O_T) string);
        }
        return Optional.empty();
    }
}
