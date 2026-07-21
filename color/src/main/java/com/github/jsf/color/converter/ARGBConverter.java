package com.github.jsf.color.converter;

import com.github.jsf.color.exceptions.ColorConversionException;
import com.github.jsf.color.validator.ARGBValidator;

import java.util.Arrays;

public class ARGBConverter implements ColorConverter<int[]> {
    private ARGBConverter() {
    }

    private record Holder() {
        private static final ARGBConverter INSTANCE = new ARGBConverter();
    }

    public static ARGBConverter getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Confirms that the incoming array is already a valid ARGB color format.
     * Since the system natively works with ARGB arrays, this serves as an identity operation with validation.
     *
     * @param color An int array representing the ARGB color components.
     * @return The same int array if validation passes.
     * @throws ColorConversionException If the input array fails validation rules.
     */
    @Override
    public int[] toARGB(int[] color) throws ColorConversionException {
        try {
            // Check that the structure matches expected ARGB parameters
            ARGBValidator.getInstance().validate(color);
            return color;
        } catch (Throwable t) {
            // Wrap any validation failures into a custom exception
            throw new ColorConversionException("Color " + Arrays.toString(color) + " conversion failed.", t);
        }
    }

    /**
     * Confirms that the incoming array is already a valid ARGB color format.
     * Since the system natively works with ARGB arrays, this serves as an identity operation with validation.
     *
     * @param argb An int array representing the ARGB color components.
     * @return The same int array if validation passes.
     * @throws ColorConversionException If the input array fails validation rules.
     */
    @Override
    public int[] fromARGB(int[] argb) throws ColorConversionException {
        try {
            // Check that the structure matches expected ARGB parameters
            ARGBValidator.getInstance().validate(argb);
            return argb;
        } catch (Throwable t) {
            // Wrap any validation failures into a custom exception
            throw new ColorConversionException("Color " + Arrays.toString(argb) + " conversion failed.", t);
        }
    }

    /**
     * A utility helper method that checks if an integer color array has an Alpha channel.
     * If the array represents a standard 3-element RGB color, it returns a new 4-element
     * array with an added opaque Alpha channel (255) at the beginning.
     *
     * @param argbOrRGB An int array that is either 3 elements (RGB) or 4 elements (ARGB).
     * @return A valid 4-element ARGB array.
     */
    public static int[] ensureAlphaChannel(int[] argbOrRGB) {
        // Run a baseline integrity check on the input array first
        ARGBValidator.getInstance().validate(argbOrRGB);

        // If the array already contains 4 elements, it is already in ARGB format
        if (argbOrRGB.length == 4) return argbOrRGB;

        // If it's a 3-element RGB array, prepend 255 (fully opaque alpha) and shift RGB down
        return new int[]{255, argbOrRGB[0], argbOrRGB[1], argbOrRGB[2]};
    }

    /**
     * Normalizes 8-bit integer channel values (0 to 255) into fractional double values (0.0 to 1.0).
     *
     * @param color An int array containing the color components.
     * @return A double array containing the normalized [Alpha, Red, Green, Blue] values.
     */
    public static double[] normalize(int[] color) {
        // Ensure the input array has an alpha channel before scaling it
        int[] argb = ensureAlphaChannel(color);

        // Divide each 8-bit channel by 255.0 to map it to a decimal range between 0.0 and 1.0
        return new double[]{argb[0] / 255D, argb[1] / 255D, argb[2] / 255D, argb[3] / 255D};
    }
}
