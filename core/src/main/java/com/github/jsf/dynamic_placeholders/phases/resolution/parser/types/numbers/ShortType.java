package com.github.jsf.dynamic_placeholders.phases.resolution.parser.types.numbers;

import com.github.jsf.text.Text;

public class ShortType extends NumberType<Short> {
    @Override
    protected Short parse(Text str) throws NumberFormatException {
        return str.asShort();
    }
}
