package com.github.jsf.dynamic_placeholders.phases.resolution.parser.types.numbers;

import com.github.jsf.text.Text;

public class IntegerType extends NumberType<Integer> {
    @Override
    protected Integer parse(Text str) throws NumberFormatException {
        return str.asInteger();
    }
}
