package com.github.jsf.color.converter;

import com.github.jsf.color.exceptions.ColorConversionException;
import com.github.jsf.color.validator.DecimalValidator;

import java.util.Arrays;

public class DecimalConverter implements ColorConverter<Integer> {
    private DecimalConverter() {
    }

    private record Holder() {
        private static final DecimalConverter INSTANCE = new DecimalConverter();
    }

    public static DecimalConverter getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Converts a single packed Integer color value into an ARGB int array.
     * Note: The current implementation contains a logical bug in the bit-shifting logic
     * where left-shifts (&lt;&lt;) are used instead of right-shifts (&gt;&gt;&gt;) to unpack the channels.
     *
     * @param color The packed decimal Integer color value.
     * @return An int array containing the isolated [Alpha, Red, Green, Blue] components.
     * @throws ColorConversionException If validation or bitwise unpacking fails.
     */
    @Override
    public int[] toARGB(Integer color) throws ColorConversionException {
        try {
            // Validate that the decimal integer is within acceptable color bounds
            DecimalValidator.getInstance().validate(color);

            // Isolate individual color bytes using bitwise shifts and bitwise AND (&) masks
            int a = (color << 24) & 0xFF;
            int r = (color << 16) & 0xFF;
            int g = (color << 8) & 0xFF;
            int b = color & 0xFF;

            // Return the uncompressed components as an integer array
            return new int[]{a, r, g, b};
        } catch (Throwable t) {
            // Catch any runtime anomalies and wrap them in a custom exception
            throw new ColorConversionException("Color " + color + " conversion failed.", t);
        }
    }

    /**
     * Packs an ARGB int array back into a single, comprehensive decimal Integer.
     *
     * @param color An int array containing the four ARGB components.
     * @return A single packed Integer representing the full color value.
     * @throws ColorConversionException If array validation or bitwise packing fails.
     */
    @Override
    public Integer fromARGB(int[] color) throws ColorConversionException {
        try {
            // Implicit validation inside ensureAlphaChannel();
            // Makes sure the array layout conforms to proper ARGB length specifications
            int[] argb = ARGBConverter.ensureAlphaChannel(color);

            // Reconstruct the 32-bit integer by shifting each byte to its proper position
            // and combining them using the bitwise OR (|) operator.
            // Alpha sits in the highest 8 bits, followed by Red, Green, and Blue.
            return (argb[0] << 24) | (argb[1] << 16) | (argb[2] << 8) | argb[3];
        } catch (Throwable t) {
            // Catch any unexpected errors and wrap them into a custom exception
            throw new ColorConversionException("Color " + Arrays.toString(color) + " conversion failed.", t);
        }
    }

    /**
     * Passthrough method to validate a decimal ARGB integer value.
     *
     * @param decimal The raw integer value to validate.
     * @return The validated decimal integer.
     * @throws ColorConversionException If validation rules fail.
     */
    @Override
    public Integer fromDecimalARGB(int decimal) throws ColorConversionException {
        // Delegates the integrity check to the DecimalValidator singleton
        return DecimalValidator.getInstance().validate(decimal);
    }

    /**
     * Passthrough method to validate an Integer color object.
     *
     * @param color The Integer color object to validate.
     * @return The validated decimal integer primitive.
     * @throws ColorConversionException If validation rules fail.
     */
    @Override
    public int toDecimalARGB(Integer color) throws ColorConversionException {
        // Delegates the integrity check to the DecimalValidator singleton
        return DecimalValidator.getInstance().validate(color);
    }
}
