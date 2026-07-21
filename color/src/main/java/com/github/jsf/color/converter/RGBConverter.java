package com.github.jsf.color.converter;

import com.github.jsf.color.exceptions.ColorConversionException;

import java.util.Arrays;

public class RGBConverter implements ColorConverter<int[]> {
    private RGBConverter() {
    }

    private record Holder() {
        private static final RGBConverter INSTANCE = new RGBConverter();
    }

    public static RGBConverter getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Converts a given RGB color array to an ARGB color array by ensuring
     * the presence of an alpha (transparency) channel.
     *
     * @param rgb An array of integers representing the RGB color components.
     * @return An array of integers representing the color with an alpha channel (ARGB).
     * @throws ColorConversionException If the conversion process encounters any error.
     */
    @Override
    public int[] toARGB(int[] rgb) throws ColorConversionException {
        try {
            // Delegates the task to a helper utility to add or verify the alpha channel
            return ARGBConverter.ensureAlphaChannel(rgb);
        } catch (Throwable t) {
            throw new ColorConversionException("Color " + Arrays.toString(rgb) + " conversion failed.", t);
        }
    }

    /**
     * Converts an ARGB color array back to a standard RGB color array by
     * stripping away the alpha (transparency) channel.
     *
     * @param color An array of integers representing the ARGB color components.
     * @return An array of integers containing only the R, G, and B components.
     * @throws ColorConversionException If the extraction process encounters any error.
     */
    @Override
    public int[] fromARGB(int[] color) throws ColorConversionException {
        try {
            int[] argb = ARGBConverter.ensureAlphaChannel(color);

            // Extracts only the Red, Green, and Blue channels, skipping the Alpha channel at index 0
            return new int[]{argb[1], argb[2], argb[3]};
        } catch (Throwable t) {
            throw new ColorConversionException("Color " + Arrays.toString(color) + " conversion failed.", t);
        }
    }
}
