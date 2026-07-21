package com.github.jsf.dynamic_placeholders.phases.resolution.parser.types.others.colors;

import com.github.jsf.color.Color;
import com.github.jsf.dynamic_placeholders.names.UName;
import com.github.jsf.dynamic_placeholders.phases.resolution.scanner.beans.DPDelimiterSet;
import com.github.jsf.scanners.delimiters.Delimiter;
import com.github.jsf.scanners.delimiters.StringDelimiter;
import com.github.jsf.text.Text;

import java.util.Optional;

public class HexColorType extends ColorType {
    public static final UName HEX_DELIMITER_IDENTIFIER = new UName("HEX_DELIMITER");
    public static final StringDelimiter HEX_DELIMITER_DEFAULT_VALUE = Delimiter.of("#");

    @Override
    protected Optional<Color> _is(Text str, DPDelimiterSet set) {
        String delimiter = getDelimiter(set).value();

        if (str.startsWith(delimiter)) {
            String value = str.substring(delimiter.length());
            return Optional.of(Color.ofHex(value));
        }

        return Optional.empty();
    }

    @Override
    public <O_T> Optional<O_T> as(Color value, Class<? extends O_T> type) {
        if (String.class.isAssignableFrom(type)) {
            String string = value.toHex();
            return Optional.of((O_T) string);
        }
        return Optional.empty();
    }

    private StringDelimiter getDelimiter(DPDelimiterSet set) {
        return set.retrieve(HEX_DELIMITER_IDENTIFIER)
                .filter(del -> del instanceof StringDelimiter)
                .map(del -> (StringDelimiter) del)
                .orElse(HEX_DELIMITER_DEFAULT_VALUE);
    }
}
