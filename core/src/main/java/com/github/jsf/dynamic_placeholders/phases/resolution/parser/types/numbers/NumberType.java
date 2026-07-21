package com.github.jsf.dynamic_placeholders.phases.resolution.parser.types.numbers;

import com.github.jsf.dynamic_placeholders.phases.resolution.parser.types.ParameterType;
import com.github.jsf.dynamic_placeholders.phases.resolution.scanner.beans.DPDelimiterSet;
import com.github.jsf.text.Text;

import javax.swing.*;
import java.util.Optional;

public abstract class NumberType<N extends Number> extends ParameterType<N> {

    protected abstract N parse(Text str) throws NumberFormatException;

    @Override
    protected Optional<N> _is(Text str, DPDelimiterSet set) {
        if (str.matches("\\d+")) {
            try {
                N number = parse(str);
                return Optional.ofNullable(number);
            } catch (NumberFormatException _) {
            }
        }
        return Optional.empty();
    }

    @Override
    public <O_T> Optional<O_T> as(N value, Class<? extends O_T> type) {
        if (String.class.isAssignableFrom(type)) {
            String string = value.toString();
            return Optional.of((O_T) string);
        }

        return Optional.empty();
    }
}
