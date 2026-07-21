package com.github.jsf.dynamic_placeholders.phases.resolution.parser.types;

import com.github.jsf.dynamic_placeholders.phases.resolution.parser.types.text.StringType;
import com.github.jsf.dynamic_placeholders.phases.resolution.scanner.beans.DPDelimiterSet;
import com.github.jsf.text.Text;

import java.util.Optional;

public abstract class ParameterType<T> {
    protected boolean hadQuotes;

    protected abstract Optional<T> _is(Text str, DPDelimiterSet set);

    public <O_T> Optional<O_T> as(T value, Class<? extends O_T> type) {
        return Optional.empty();
    }

    public Optional<T> is(Text str, DPDelimiterSet set) {
        Text unquoted = str.unquote();
        hadQuotes = str != unquoted;
        return _is(unquoted, set);
    }

    public static Type typize(Text text, DPDelimiterSet set) {
        for (ParameterType<?> type : ParameterTypes.all()) {
            Optional<?> object = type.is(text, set);
            if (object.isPresent()) return new Type(type, object.get());
        }
        return new Type(new StringType(), text.unquote().toString());
    }

    public record Type(ParameterType<?> type, Object object) {}

}
