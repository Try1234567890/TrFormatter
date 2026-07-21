package com.github.jsf.dynamic_placeholders.phases.resolution.parser.types.text;

import com.github.jsf.dynamic_placeholders.phases.resolution.parser.types.ParameterType;
import com.github.jsf.dynamic_placeholders.phases.resolution.scanner.beans.DPDelimiterSet;
import com.github.jsf.text.Text;

import java.util.Optional;

public class StringType extends ParameterType<String> {
    @Override
    protected Optional<String> _is(Text str, DPDelimiterSet set) {
        return Optional.of(str.toString());
    }

    @Override
    public <O_T> Optional<O_T> as(String value, Class<? extends O_T> type) {
        if (Character.class.isAssignableFrom(type)) {
            @SuppressWarnings("WrapperTypeMayBePrimitive") // cannot be
            Character ch = value.isEmpty() ? '\000' : value.charAt(0);
            return Optional.of((O_T) ch);
        }
        return Optional.empty();
    }
}
