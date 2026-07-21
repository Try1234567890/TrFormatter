package com.github.jsf.dynamic_placeholders.phases.resolution.parser.types.numbers;

import com.github.jsf.text.Text;

public class ByteType extends NumberType<Byte> {
    @Override
    protected Byte parse(Text str) throws NumberFormatException {
        return str.asByte();
    }
}
