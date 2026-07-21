package com.github.jsf.dynamic_placeholders.phases.resolution.parser.types.numbers;

import com.github.jsf.text.Text;

public class DoubleType extends NumberType<Double> {
    @Override
    protected Double parse(Text str) throws NumberFormatException {
        return str.asDouble();
    }
}
