package com.github.jsf.color.converter;

import com.github.jsf.color.exceptions.ColorConversionException;
import com.github.jsf.color.validator.IntegerHexValidator;

import java.util.Arrays;

public class IntegerHexConverter implements ColorConverter<Integer> {
    private IntegerHexConverter() {
    }

    private record Holder() {
        private static final IntegerHexConverter INSTANCE = new IntegerHexConverter();
    }

    public static IntegerHexConverter getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Converts an Integer color value into an ARGB int array.
     * It achieves this by first converting the Integer to its hexadecimal string representation.
     *
     * @param color The color represented as an Integer.
     * @return An int array containing the ARGB components.
     * @throws ColorConversionException If any error occurs during the hex conversion or parsing process.
     */
    @Override
    public int[] toARGB(Integer color) throws ColorConversionException {
        try {
            // Convert the standard Integer color into a raw Hexadecimal string format
            String asHex = Integer.toHexString(color);
            // Delegate to the singleton converter to parse the Hex string into an ARGB array
            return StringHexConverter.getInstance().toARGB(asHex);
        } catch (Throwable t) {
            // Catch any unexpected exceptions and wrap them inside a custom exception
            throw new ColorConversionException("Color " + color + " conversion failed.", t);
        }
    }

    /**
     * Converts an ARGB int array back into a single Integer color value.
     * It formats the array into a hex string, which is then parsed as a base-16 Integer.
     *
     * @param argb An int array containing the ARGB components.
     * @return The color represented as a single Integer.
     * @throws ColorConversionException If any error occurs during the conversion or string parsing.
     */
    @Override
    public Integer fromARGB(int[] argb) throws ColorConversionException {
        try {
            // Convert the ARGB components array into a Hexadecimal string representation
            String asHex = StringHexConverter.getInstance().fromARGB(argb);
            // Parse the Hex string back into a standard base-16 (Hexadecimal) Integer value
            return Integer.parseInt(asHex, 16);
        } catch (Throwable t) {
            // Catch any unexpected exceptions and wrap them inside a custom exception
            throw new ColorConversionException("Color " + Arrays.toString(argb) + " conversion failed.", t);
        }
    }

    /**
     * Validates an integer-based hexadecimal color value.
     *
     * @param hex The raw hex integer value to validate.
     * @return The validated Integer color value.
     * @throws ColorConversionException If the validation rules fail.
     */
    @Override
    public Integer fromIntegerHex(int hex) throws ColorConversionException {
        // Delegates the validation process to the IntegerHexValidator singleton instance
        return IntegerHexValidator.getInstance().validate(hex);
    }

    /**
     * Validates an Integer color value to ensure it matches required hexadecimal formats.
     *
     * @param color The Integer color value to validate.
     * @return The validated raw hex integer.
     * @throws ColorConversionException If the validation rules fail.
     */
    @Override
    public int toIntegerHex(Integer color) throws ColorConversionException {
        // Delegates the validation process to the IntegerHexValidator singleton instance
        return IntegerHexValidator.getInstance().validate(color);
    }
}
