package com.github.jsf.dynamic_placeholders.phases.resolution.parser.types.numbers;

import com.github.jsf.text.Text;

public class LongType extends NumberType<Long> {
    @Override
    protected Long parse(Text str) throws NumberFormatException {
        return str.asLong();
    }
}
