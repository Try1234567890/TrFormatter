package com.github.jsf.color;

import com.github.jsf.color.ansi.ANSI24Bit;
import com.github.jsf.color.ansi.ANSI3Bit;
import com.github.jsf.color.ansi.ANSI4Bit;
import com.github.jsf.color.ansi.ANSI8Bit;
import com.github.jsf.color.converter.*;
import com.github.jsf.color.exceptions.InvalidColorException;
import com.github.utilities.validators.Preconditions;

/**
 * This is class contains represent internally the color as ARGB (Alpha, Red, Green, Blue) model.
 * It can be constructed with all different supported models (ARGB, RGB, Decimal, Hex, IntegerHex, HSB, HSL)
 * and can be converted to all different supported models too.
 */
public class Color {
    /**
     * The alpha, red, green and blue channels of the color.
     */
    private final int a, r, g, b;
    private final int[] argb;

    /**
     * Creates a new color from the given ARGB channels.
     *
     * @param argb the ARGB channels of the color
     * @throws InvalidColorException if the channels are not valid.
     */
    public Color(int[] argb) {
        this.argb = ARGBConverter.ensureAlphaChannel(argb); // Validate the channels too.
        this.a = argb[0];
        this.r = argb[1];
        this.g = argb[2];
        this.b = argb[3];
    }

    /**
     * Creates a new color from the given ARGB channels.
     *
     * @param argb the ARGB channels of the color
     * @return The new color.
     * @throws InvalidColorException if the channels are not valid.
     */
    public static Color ofARGB(int[] argb) {
        // Validation are made in the constructor
        return new Color(argb);
    }

    /**
     * Creates a new color from the given ARGB channels.
     *
     * @param a The {@code alpha} channel of the color.
     * @param r The {@code red} channel of the color.
     * @param g The {@code green} channel of the color.
     * @param b The {@code blue} channel of the color.
     * @return The new color.
     * @throws InvalidColorException if the channels are not valid.
     */
    public static Color ofARGB(int a, int r, int g, int b) {
        // Validation are made in the constructor
        return ofARGB(new int[]{a, r, g, b});
    }


    /**
     * Creates a new color from the given RGB channels.
     *
     * @param r The {@code red} channel of the color.
     * @param g The {@code green} channel of the color.
     * @param b The {@code blue} channel of the color.
     * @return The new color.
     * @throws InvalidColorException if the channels are not valid.
     */
    public static Color ofRGB(int r, int g, int b) {
        // Validation are made in the constructor
        return ofARGB(new int[]{255, r, g, b});
    }

    /**
     * Creates a new color from the given decimal RGB value.
     *
     * @param decimal The decimal representation of the color.
     * @return The new color.
     * @throws InvalidColorException if the decimal representation is not valid.
     */
    public static Color ofDecimalRGB(int decimal) {
        return ofARGB(DecimalConverter.getInstance().toARGB(decimal));
    }

    /**
     * Creates a new color from the given hex.
     *
     * @param hex The hex representation of the color.
     * @return The new color.
     * @throws InvalidColorException if the hex color is not valid.
     */
    public static Color ofHex(String hex) {
        return ofARGB(StringHexConverter.getInstance().toARGB(hex));
    }


    /**
     * Creates a new color from the given integer hex.
     *
     * @param hex The integer hex representation of the color.
     * @return The new color.
     * @throws InvalidColorException if the integer hex color is not valid.
     */
    public static Color ofIntegerHex(int hex) {
        return ofARGB(IntegerHexConverter.getInstance().toARGB(hex));
    }


    /**
     * Creates a new color from the given hsb.
     *
     * @param hsb The hsb representation of the color.
     * @return The new color.
     * @throws InvalidColorException if the hsb color is not valid.
     */
    public static Color ofHSB(double[] hsb) {
        return ofARGB(HSBConverter.getInstance().toARGB(hsb));
    }


    /**
     * Creates a new color from the given hsl.
     *
     * @param hsl The hsl representation of the color.
     * @return The new color.
     * @throws InvalidColorException if the hsl color is not valid.
     */
    public static Color ofHSL(double[] hsl) {
        return ofARGB(HSLConverter.getInstance().toARGB(hsl));
    }


    /**
     * Creates a new color from the given AWT color.
     *
     * @param color The color representation of the AWT color.
     * @return The new color.
     * @throws InvalidColorException if the AWT color is not valid.
     */
    public static Color ofAWTColor(java.awt.Color color) {
        Preconditions.parameterNotNull(color, "color");
        // Validation are made in the constructor
        return ofARGB(new int[]{color.getAlpha(), color.getRed(), color.getGreen(), color.getBlue()});
    }

    /**
     * Returns the alpha channel of the color.
     *
     * @return The alpha channel of the color.
     */
    public int getA() {
        return a;
    }

    /**
     * Returns the red channel of the color.
     *
     * @return The red channel of the color.
     */
    public int getR() {
        return r;
    }

    /**
     * Returns the green channel of the color.
     *
     * @return The green channel of the color.
     */
    public int getG() {
        return g;
    }

    /**
     * Returns the blue channel of the color.
     *
     * @return The blue channel of the color.
     */
    public int getB() {
        return b;
    }

    /**
     * Returns the ARGB representation of the color.
     *
     * @return The ARGB representation of the color.
     */
    public int[] toARGB() {
        return argb;
    }

    /**
     * Converts this color to the RGB color model.
     *
     * @return the RGB representation of this color.
     */
    public int[] toRGB() {
        return RGBConverter.getInstance().fromARGB(argb);
    }

    /**
     * Converts this color to the decimal RGB color model.
     *
     * @return the decimal RGB representation of this color.
     */
    public int toDecimalRGB() {
        return DecimalConverter.getInstance().fromARGB(argb);
    }

    /**
     * Converts this color to the HSL color model.
     *
     * @return the HSL representation of this color.
     */
    public double[] toHSL() {
        return HSLConverter.getInstance().fromARGB(argb);
    }

    /**
     * Converts this color to the HSB color model.
     *
     * @return the HSB representation of this color.
     */
    public double[] toHSB() {
        return HSBConverter.getInstance().fromARGB(argb);
    }

    /**
     * Converts this color to the Integer-Hex color model.
     *
     * @return the Integer-Hex representation of this color.
     */
    public int toIntegerHex() {
        return IntegerHexConverter.getInstance().fromARGB(argb);
    }

    /**
     * Converts this color to the String-Hex color model.
     *
     * @return the String-Hex representation of this color.
     */
    public String toHex() {
        return StringHexConverter.getInstance().fromARGB(argb);
    }

    /**
     * Converts this color to the ANSI3Bit color model.
     *
     * @return the nearest ANSI3Bit representation of this color.
     */
    public ANSI3Bit to3BitAnsi() {
        return ANSI3Bit.getNearestFromARGB(toARGB());
    }

    /**
     * Converts this color to the ANSI4Bit color model.
     *
     * @return the nearest ANSI4Bit representation of this color.
     */
    public ANSI4Bit to4BitAnsi() {
        return ANSI4Bit.getNearestFromARGB(toARGB());
    }

    /**
     * Converts this color to the ANSI8Bit color model.
     *
     * @return the nearest ANSI8Bit representation of this color.
     */
    private ANSI8Bit to8BitAnsi() {
        return ANSI8Bit.getNearestFromARGB(toARGB());
    }

    /**
     * Converts this color to the ANSI24Bit color model.
     *
     * @return the ANSI24Bit representation of this color.
     */
    public ANSI24Bit to24BitAnsi() {
        return ANSI24Bit.ofARGB(toARGB());
    }
}
