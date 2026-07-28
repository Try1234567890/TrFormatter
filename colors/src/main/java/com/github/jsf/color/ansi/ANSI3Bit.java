package com.github.jsf.color.ansi;

import com.github.jsf.color.converter.*;

public class ANSI3Bit implements ANSI {
    public static final ANSI3Bit BLACK = new ANSI3Bit("BLACK", 30, 0, 0, 0);
    public static final ANSI3Bit RED = new ANSI3Bit("RED", 31, 204, 0, 0);
    public static final ANSI3Bit GREEN = new ANSI3Bit("GREEN", 32, 78, 154, 6);
    public static final ANSI3Bit YELLOW = new ANSI3Bit("YELLOW", 33, 196, 160, 0);
    public static final ANSI3Bit BLUE = new ANSI3Bit("BLUE", 34, 114, 159, 207);
    public static final ANSI3Bit MAGENTA = new ANSI3Bit("MAGENTA", 35, 117, 80, 123);
    public static final ANSI3Bit CYAN = new ANSI3Bit("CYAN", 36, 6, 152, 154);
    public static final ANSI3Bit WHITE = new ANSI3Bit("WHITE", 37, 211, 215, 207);
    public static final ANSI3Bit[] VALUES = new ANSI3Bit[]{BLACK, RED, GREEN, YELLOW, BLUE, MAGENTA, CYAN, WHITE};

    public static ANSI3Bit[] values() {
        return VALUES;
    }


    public static ANSI3Bit getNearestFromHSL(double[] hsb) {
        return getNearestFromARGB(HSLConverter.getInstance().toARGB(hsb));
    }

    public static ANSI3Bit getNearestFromHSB(double[] hsl) {
        return getNearestFromARGB(HSBConverter.getInstance().toARGB(hsl));
    }

    public static ANSI3Bit getNearestFromHex(int hex) {
        return getNearestFromARGB(IntegerHexConverter.getInstance().toARGB(hex));
    }

    public static ANSI3Bit getNearestFromHex(String hex) {
        return getNearestFromARGB(StringHexConverter.getInstance().toARGB(hex));
    }

    public static ANSI3Bit getNearestFromRGB(int rgb) {
        return getNearestFromARGB(DecimalConverter.getInstance().toARGB(rgb));
    }

    public static ANSI3Bit getNearestFromRGB(int[] rgb) {
        return getNearestFromARGB(rgb);
    }

    public static ANSI3Bit getNearestFromARGB(int[] argb) {
        return ANSI.getNearest(argb, values());
    }

    private final String name;
    private final int code;
    private final int r;
    private final int g;
    private final int b;


    protected ANSI3Bit(String name, int code, int r, int g, int b) {
        this.name = name;
        this.code = code;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public int getR() {
        return r;
    }

    @Override
    public int getG() {
        return g;
    }

    @Override
    public int getB() {
        return b;
    }

    @Override
    public String getTextEscapeSequence() {
        return "\u001b[38;5;" + getCode() + "m";
    }

    @Override
    public String getBackgroundEscapeSequence() {
        return "\u001b[48;5;" + getCode() + "m";
    }
}
