package com.github.jsf.color.ansi;

import com.github.jsf.color.converter.*;
import com.github.jsf.color.exceptions.InvalidColorException;

/**
 * Represents a 24-bit TrueColor (RGB) ANSI escape sequence implementation.
 * This class allows formatting terminal text or backgrounds with millions of colors
 * by utilizing standard 32-bit internal conversions to extract structural R, G, and B values.
 */
public final class ANSI24Bit implements ANSI {
    // Standard ANSI escape prefix for 24-bit foreground/text colors: ESC[38;2;R;G;Bm
    public static final String TEXT_PREFIX = "\u001B[38;2;";
    // Standard ANSI escape prefix for 24-bit background colors: ESC[48;2;R;G;Bm
    public static final String BACK_PREFIX = "\u001B[48;2;";

    private final int r, g, b;

    // Private constructor forces instantiation exclusively via static factory methods ("of...")
    private ANSI24Bit(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    /**
     * Factory method: Creates an ANSI24Bit instance from an integer-based hexadecimal color.
     */
    public static ANSI24Bit ofHex(int hex) throws InvalidColorException {
        int[] rgb = IntegerHexConverter.getInstance().toARGB(hex);
        return ofARGB(rgb);
    }

    /**
     * Factory method: Creates an ANSI24Bit instance from a Hexadecimal String color.
     */
    public static ANSI24Bit ofHex(String hex) throws InvalidColorException {
        int[] rgb = StringHexConverter.getInstance().toARGB(hex);
        return ofARGB(rgb);
    }

    /**
     * Factory method: Creates an ANSI24Bit instance from an HSL double array.
     */
    public static ANSI24Bit ofHSL(double[] hsl) throws InvalidColorException {
        int[] rgb = HSLConverter.getInstance().toARGB(hsl);
        return ofARGB(rgb);
    }

    /**
     * Factory method: Creates an ANSI24Bit instance from an HSB double array.
     */
    public static ANSI24Bit ofHSB(double[] hsb) throws InvalidColorException {
        int[] rgb = HSBConverter.getInstance().toARGB(hsb);
        return ofARGB(rgb);
    }

    /**
     * Factory method: Creates an ANSI24Bit instance from a packed 32-bit decimal integer.
     */
    public static ANSI24Bit ofDecimal(int decimal) throws InvalidColorException {
        int[] rgb = DecimalConverter.getInstance().toARGB(decimal);
        return ofARGB(rgb);
    }

    /**
     * Core factory method: Unpacks a 4-element ARGB array into a concrete 24-bit RGB instance.
     * All other factory methods route their intermediate arrays through this entry point.
     *
     * @param argb The source color components array.
     * @return A new instance of ANSI24Bit.
     * @throws InvalidColorException If formatting or channel validation checks fail.
     */
    public static ANSI24Bit ofARGB(int[] argb) throws InvalidColorException {
        int[] rgb = ARGBConverter.ensureAlphaChannel(argb);
        return new ANSI24Bit(rgb[1], rgb[2], rgb[3]);
    }

    /**
     * Gets a readable, string-formatted identifier for the RGB color value.
     */
    @Override
    public String getName() {
        return "RGB[" + r + ", " + g + ", " + b + "]";
    }

    /**
     * Fallback code method. 24-bit TrueColor does not match discrete standard indexed values (like 0-15 or 0-255 ranges).
     *
     * @return 0 as a default structural placeholder value.
     */
    @Override
    public int getCode() {
        return 0;
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

    /**
     * Generates the complete escape sequence required to change console text color.
     * Example Output: "\u001B[38;2;255;0;0m" (Turns subsequent terminal text bright red)
     */
    @Override
    public String getTextEscapeSequence() {
        return TEXT_PREFIX + getR() + ";" + getG() + ";" + getB() + "m";
    }

    /**
     * Generates the complete escape sequence required to change console background color.
     * Example Output: "\u001B[48;2;0;0;255m" (Turns subsequent terminal backgrounds bright blue)
     */
    @Override
    public String getBackgroundEscapeSequence() {
        return BACK_PREFIX + getR() + ";" + getG() + ";" + getB() + "m";
    }
}