package com.github.jsf.dynamic_placeholders.phases.resolution.parser.types.numbers;

import com.github.jsf.text.Text;

public class FloatType extends NumberType<Float> {
    @Override
    protected Float parse(Text str) throws NumberFormatException {
        return str.asFloat();
    }
}
