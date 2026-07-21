package com.github.jsf.color.converter;

import com.github.jsf.color.exceptions.ColorConversionException;
import com.github.jsf.color.validator.HSB_HSLValidator;

public class HSBConverter extends HSLConverter {
    private HSBConverter() {
    }

    private record Holder() {
        private static final HSBConverter INSTANCE = new HSBConverter();
    }

    public static HSBConverter getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Overrides the HSL saturation calculation to compute HSB-specific Saturation.
     * In HSB (HSV), saturation is defined as the ratio of Chroma (c) to the Maximum color value (v).
     *
     * @param c Chroma value (difference between max and min RGB channels).
     * @param v The maximum value among the normalized RGB channels (corresponds to Brightness).
     * @param l Pre-calculated Lightness (inherited from the parent structure, unused here).
     * @return The Saturation component ranging between 0.0 and 1.0.
     */
    @Override
    protected double getSaturation(double c, double v, double l) {
        // Guard clause: Avoid division by zero if the brightness/value is absolute black (0.0)
        if (v == 0.0) return 0;

        // HSB Saturation formula: S = Chroma / Value
        return c / v;
    }

    /**
     * Passthrough method to validate an HSB array structure.
     *
     * @param hsb A double array containing HSB components.
     * @return The validated HSB double array.
     * @throws ColorConversionException If validation rules fail.
     */
    @Override
    public double[] fromHSB(double[] hsb) throws ColorConversionException {
        // Delegates the boundary validation check to the HSB_HSLValidator singleton
        return HSB_HSLValidator.getInstance().validate(hsb);
    }

    /**
     * Passthrough method to validate an HSB array structure.
     *
     * @param color A double array containing HSB components.
     * @return The validated HSB double array.
     * @throws ColorConversionException If validation rules fail.
     */
    @Override
    public double[] toHSB(double[] color) throws ColorConversionException {
        // Delegates the boundary validation check to the HSB_HSLValidator singleton
        return HSB_HSLValidator.getInstance().validate(color);
    }
}