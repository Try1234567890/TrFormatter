package com.github.jsf.color.converter;


import com.github.jsf.color.exceptions.ColorConversionException;

public interface ColorConverter<V> {

    int[] toARGB(V color) throws ColorConversionException;

    V fromARGB(int[] argb) throws ColorConversionException;

    default String toHex(V color) throws ColorConversionException {
        int[] argb = toARGB(color);
        return StringHexConverter.getInstance().fromARGB(argb);
    }

    default V fromHex(String hex) throws ColorConversionException {
        int[] argb = StringHexConverter.getInstance().toARGB(hex);
        return fromARGB(argb);
    }

    default int toIntegerHex(V color) throws ColorConversionException {
        int[] argb = toARGB(color);
        return IntegerHexConverter.getInstance().fromARGB(argb);
    }

    default V fromIntegerHex(int hex) throws ColorConversionException {
        int[] argb = IntegerHexConverter.getInstance().toARGB(hex);
        return fromARGB(argb);
    }

    default int toDecimalARGB(V color) throws ColorConversionException {
        int[] argb = toARGB(color);
        return DecimalConverter.getInstance().fromARGB(argb);
    }

    default V fromDecimalARGB(int decimal) throws ColorConversionException {
        int[] argb = DecimalConverter.getInstance().toARGB(decimal);
        return fromARGB(argb);
    }

    default double[] toHSB(V color) throws ColorConversionException {
        int[] argb = toARGB(color);
        return HSBConverter.getInstance().fromARGB(argb);
    }

    default V fromHSB(double[] hsb) throws ColorConversionException {
        int[] argb = HSBConverter.getInstance().toARGB(hsb);
        return fromARGB(argb);
    }


    default double[] toHSL(V color) throws ColorConversionException {
        int[] argb = toARGB(color);
        return HSLConverter.getInstance().fromARGB(argb);
    }

    default V fromHSL(double[] hsl) throws ColorConversionException {
        int[] argb = HSLConverter.getInstance().toARGB(hsl);
        return fromARGB(argb);
    }
}
