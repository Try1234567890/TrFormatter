package com.github.jsf.color.ansi;

import com.github.jsf.color.converter.*;

public class ANSI4Bit extends ANSI3Bit {
    public static final ANSI4Bit BLACK = new ANSI4Bit("BLACK", 30, 0, 0, 0);
    public static final ANSI4Bit RED = new ANSI4Bit("RED", 31, 204, 0, 0);
    public static final ANSI4Bit GREEN = new ANSI4Bit("GREEN", 32, 78, 154, 6);
    public static final ANSI4Bit YELLOW = new ANSI4Bit("YELLOW", 33, 196, 160, 0);
    public static final ANSI4Bit BLUE = new ANSI4Bit("BLUE", 34, 114, 159, 207);
    public static final ANSI4Bit MAGENTA = new ANSI4Bit("MAGENTA", 35, 117, 80, 123);
    public static final ANSI4Bit CYAN = new ANSI4Bit("CYAN", 36, 6, 152, 154);
    public static final ANSI4Bit WHITE = new ANSI4Bit("WHITE", 37, 211, 215, 207);
    public static final ANSI4Bit BRIGHT_BLACK = new ANSI4Bit("BRIGHT_BLACK", 90, 85, 87, 83);
    public static final ANSI4Bit BRIGHT_RED = new ANSI4Bit("BRIGHT_RED", 91, 239, 41, 41);
    public static final ANSI4Bit BRIGHT_GREEN = new ANSI4Bit("BRIGHT_GREEN", 92, 138, 226, 52);
    public static final ANSI4Bit BRIGHT_YELLOW = new ANSI4Bit("BRIGHT_YELLOW", 93, 252, 233, 79);
    public static final ANSI4Bit BRIGHT_BLUE = new ANSI4Bit("BRIGHT_BLUE", 94, 50, 175, 255);
    public static final ANSI4Bit BRIGHT_MAGENTA = new ANSI4Bit("BRIGHT_MAGENTA", 95, 173, 127, 168);
    public static final ANSI4Bit BRIGHT_CYAN = new ANSI4Bit("BRIGHT_CYAN", 96, 52, 226, 226);
    public static final ANSI4Bit BRIGHT_WHITE = new ANSI4Bit("BRIGHT_WHITE", 97, 255, 255, 255);
    public static final ANSI4Bit[] VALUES = new ANSI4Bit[]{
            BLACK, RED, GREEN, YELLOW, BLUE, MAGENTA, CYAN, WHITE,
            BRIGHT_BLACK, BRIGHT_RED, BRIGHT_GREEN, BRIGHT_YELLOW, BRIGHT_BLUE, BRIGHT_MAGENTA, BRIGHT_CYAN, BRIGHT_WHITE
    };

    public static ANSI4Bit[] values() {
        return VALUES;
    }

    public static ANSI4Bit getNearestFromHSL(double[] hsb) {
        return getNearestFromARGB(HSLConverter.getInstance().toARGB(hsb));
    }

    public static ANSI4Bit getNearestFromHSB(double[] hsl) {
        return getNearestFromARGB(HSBConverter.getInstance().toARGB(hsl));
    }

    public static ANSI4Bit getNearestFromHex(int hex) {
        return getNearestFromARGB(IntegerHexConverter.getInstance().toARGB(hex));
    }

    public static ANSI4Bit getNearestFromHex(String hex) {
        return getNearestFromARGB(StringHexConverter.getInstance().toARGB(hex));
    }

    public static ANSI4Bit getNearestFromRGB(int rgb) {
        return getNearestFromARGB(DecimalConverter.getInstance().toARGB(rgb));
    }

    public static ANSI4Bit getNearestFromRGB(int[] rgb) {
        return getNearestFromARGB(rgb);
    }

    public static ANSI4Bit getNearestFromARGB(int[] argb) {
        return ANSI.getNearest(argb, values());
    }

    protected ANSI4Bit(String name, int code, int r, int g, int b) {
        super(name, code, r, g, b);
    }
}
