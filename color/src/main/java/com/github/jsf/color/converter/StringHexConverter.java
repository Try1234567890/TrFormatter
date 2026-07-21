package com.github.jsf.color.converter;

import com.github.jsf.color.exceptions.ColorConversionException;
import com.github.jsf.color.validator.StringHexValidator;

import java.util.Arrays;

public class StringHexConverter implements ColorConverter<String> {
    private StringHexConverter() {
    }

    private record Holder() {
        private static final StringHexConverter INSTANCE = new StringHexConverter();
    }

    public static StringHexConverter getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Converts a Hexadecimal color String into an ARGB int array.
     * It splits the hex string into 2-character pairs for Alpha, Red, Green, and Blue.
     *
     * @param color The color represented as a Hex string (e.g., "RRGGBB" or "AARRGGBB").
     * @return An int array containing the four ARGB components.
     * @throws ColorConversionException If parsing or validation fails.
     */
    @Override
    public int[] toARGB(String color) throws ColorConversionException {
        try {
            // Implicit validation inside ensureAlphaChannel();
            // Ensures the string is a valid hex format and pads it with a default "FF" alpha channel if it's only 6 characters long
            String hex = ensureAlphaChannel(color);

            int a = Integer.parseInt(hex.substring(0, 2), 16);
            int r = Integer.parseInt(hex.substring(2, 4), 16);
            int g = Integer.parseInt(hex.substring(4, 6), 16);
            int b = Integer.parseInt(hex.substring(6, 8), 16);

            return new int[]{a, r, g, b};
        } catch (Throwable t) {
            throw new ColorConversionException("Color " + color + " conversion failed.", t);
        }
    }

    /**
     * Converts an ARGB int array back into an 8-character uppercase Hexadecimal String.
     *
     * @param color An int array containing the ARGB components.
     * @return An 8-character Hex string formatted as "AARRGGBB".
     * @throws ColorConversionException If the input array cannot be validated or formatted.
     */
    @Override
    public String fromARGB(int[] color) throws ColorConversionException {
        try {
            int[] argb = ARGBConverter.ensureAlphaChannel(color);

            // Format each component into a 2-digit, zero-padded uppercase hexadecimal string
            return String.format("%02X%02X%02X%02X", argb[0], argb[1], argb[2], argb[3]);
        } catch (Throwable t) {
            throw new ColorConversionException("Color " + Arrays.toString(color) + " conversion failed.", t);
        }
    }

    /**
     * Validates an incoming Hexadecimal color string.
     *
     * @param hex The raw hex string to validate.
     * @return The validated hex string.
     * @throws ColorConversionException If the validation rules fail.
     */
    @Override
    public String fromHex(String hex) throws ColorConversionException {
        return StringHexValidator.getInstance().validate(hex);
    }

    /**
     * Validates an incoming color string to ensure it meets proper Hex structural guidelines.
     *
     * @param color The color string to validate.
     * @return The validated hex string.
     * @throws ColorConversionException If the validation rules fail.
     */
    @Override
    public String toHex(String color) throws ColorConversionException {
        return StringHexValidator.getInstance().validate(color);
    }

    /**
     * A utility helper method that checks if a hex string contains an Alpha channel.
     * If the string represents a standard 6-character color (RRGGBB), it prepends "FF" (fully opaque).
     *
     * @param color The raw color string.
     * @return A guaranteed 8-character hexadecimal string representing AARRGGBB.
     */
    public static String ensureAlphaChannel(String color) {
        String hex = StringHexValidator.getInstance().validate(color);
        if (hex.length() == 6) return "FF" + hex;

        return hex;
    }
}
