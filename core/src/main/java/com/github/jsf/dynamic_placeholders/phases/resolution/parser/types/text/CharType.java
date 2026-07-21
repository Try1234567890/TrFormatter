package com.github.jsf.dynamic_placeholders.phases.resolution.parser.types.text;

import com.github.jsf.dynamic_placeholders.phases.resolution.parser.types.ParameterType;
import com.github.jsf.dynamic_placeholders.phases.resolution.scanner.beans.DPDelimiterSet;
import com.github.jsf.text.Text;

import java.util.Optional;

public class CharType extends ParameterType<Character> {
    @Override
    protected Optional<Character> _is(Text str, DPDelimiterSet set) {
        if (str.length() == 1) {
            char ch = str.charAt(0);
            return Optional.of(ch);
        }
        return Optional.empty();
    }

    @Override
    public <O_T> Optional<O_T> as(Character value, Class<? extends O_T> type) {
        if (String.class.isAssignableFrom(type)) {
            String string = String.valueOf(value);
            return Optional.of((O_T) string);
        }
        return Optional.empty();
    }
}
