package com.github.jsf.color.converter;

import com.github.jsf.color.exceptions.ColorConversionException;
import com.github.jsf.color.validator.HSB_HSLValidator;

import java.util.Arrays;

public class HSLConverter implements ColorConverter<double[]> {
    protected HSLConverter() {
    }

    private record Holder() {
        private static final HSLConverter INSTANCE = new HSLConverter();
    }

    public static HSLConverter getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Converts a double array representing HSL color spaces into an ARGB int array.
     * This method implements the standard HSL-to-RGB mathematical conversion algorithm.
     *
     * @param color A double array containing Hue (0-360), Saturation (0-1), and Lightness (0-1).
     * @return An int array containing the ARGB components (with Alpha set to 255/fully opaque).
     * @throws ColorConversionException If validation or conversion logic encounters an error.
     */
    @Override
    public int[] toARGB(double[] color) throws ColorConversionException {
        try {
            // Validate the HSL boundaries using a dedicated singleton validator
            double[] hsb = HSB_HSLValidator.getInstance().validate(color);

            double h = hsb[0];
            double s = hsb[1];
            double l = hsb[2];

            // Convert hue to a normalized angle. This is a value between 0 and 5 representing the 6 sectors of the color wheel
            double hF = (h / 60.0) % 6.0;
            // Protection against negative floating-point values to keep it within [0, 6)
            if (hF < 0) hF += 6.0;

            // Calculate Chroma (c) - the structural intensity/colorfulness of the color
            double c = (1.0 - Math.abs((2.0 * l) - 1.0)) * s;
            // Calculate intermediate value (x) for the secondary color component based on the hue sector
            double x = c * (1.0 - Math.abs((hF % 2.0) - 1.0));

            double rF, gF, bF;

            // Determine fractional RGB values based on which of the 6 sectors the Hue falls into
            switch ((int) hF) {
                case 0 -> {
                    rF = c;
                    gF = x;
                    bF = 0;
                } // 0° to 60°
                case 1 -> {
                    rF = x;
                    gF = c;
                    bF = 0;
                } // 60° to 120°
                case 2 -> {
                    rF = 0;
                    gF = c;
                    bF = x;
                } // 120° to 180°
                case 3 -> {
                    rF = 0;
                    gF = x;
                    bF = c;
                } // 180° to 240°
                case 4 -> {
                    rF = x;
                    gF = 0;
                    bF = c;
                } // 240° to 300°
                case 5 -> {
                    rF = c;
                    gF = 0;
                    bF = x;
                } // 300° to 360°
                default -> throw new ColorConversionException("Invalid hue value: " + h);
            }

            // Calculate the lightness match factor (m) to scale up to the correct brightness level
            double m = l - (c / 2.0);

            // Add the lightness match factor, scale to standard 8-bit integers (0-255), and clamp bounds safely
            int r = Math.clamp(Math.round((rF + m) * 255.0), 0, 255);
            int g = Math.clamp(Math.round((gF + m) * 255.0), 0, 255);
            int b = Math.clamp(Math.round((bF + m) * 255.0), 0, 255);

            // Return the ARGB array with Alpha hardcoded to 255 (completely opaque)
            return new int[]{255, r, g, b};
        } catch (Throwable t) {
            throw new ColorConversionException("Color " + Arrays.toString(color) + " conversion failed.", t);
        }
    }

    /**
     * Converts an ARGB int array back into an HSL double array.
     *
     * @param color An int array containing ARGB components.
     * @return A double array containing [Hue, Saturation, Lightness].
     * @throws ColorConversionException If calculation errors occur.
     */
    @Override
    public double[] fromARGB(int[] color) throws ColorConversionException {
        // Normalize the 0-255 integer channels into 0.0-1.0 double values
        double[] argb = ARGBConverter.normalize(color);
        double r = argb[1];
        double g = argb[2];
        double b = argb[3];

        // Find the maximum and minimum values among the RGB channels
        double xMax = Math.max(r, Math.max(g, b));
        double xMin = Math.min(r, Math.min(g, b));

        // Chroma calculation (the difference between max and min channel values)
        double c = xMax - xMin;
        // Lightness is simply the average of the maximum and minimum components
        double l = (xMax + xMin) / 2.0;

        // Delegate calculations for Hue and Saturation components
        double h = getHue(c, xMax, r, g, b);
        double s = getSaturation(c, xMax, l);

        return new double[]{h, s, l};
    }

    /**
     * Helper method to compute the HSL Saturation component.
     *
     * @param c Chroma value.
     * @param v Max RGB component value.
     * @param l Pre-calculated Lightness value.
     * @return The Saturation value ranging between 0.0 and 1.0.
     */
    protected double getSaturation(double c, double v, double l) {
        // Avoid division by zero at absolute black or absolute white boundaries
        if (l == 0.0 || l == 1.0) {
            return 0;
        } else {
            // Scale saturation relative to Chroma and Lightness bounds
            double den = 1.0 - Math.abs((2.0 * l) - 1.0);
            return (c / den);
        }
    }

    /**
     * Helper method to compute the Hue angle (0 to 360 degrees) based on which color component is dominant.
     */
    private double getHue(double c, double v, double r, double g, double b) {
        // If there is no chroma difference, the color is grayscale; hue is undefined/0
        if (c == 0.0) return 0;

        double hUnscaled;
        if (v == r) {
            // Red is dominant
            hUnscaled = ((g - b) / c) % 6.0;
        } else if (v == g) {
            // Green is dominant
            hUnscaled = ((b - r) / c) + 2.0;
        } else {
            // Blue is dominant
            hUnscaled = ((r - g) / c) + 4.0;
        }

        // Convert the structural wheel sector value back into a 360-degree angle
        double h = hUnscaled * 60.0;
        // Keep the final angle positive
        if (h < 0.0) h += 360;
        return h;
    }

    /**
     * Passthrough method to validate an HSL array structure.
     */
    @Override
    public double[] fromHSL(double[] hsl) throws ColorConversionException {
        return HSB_HSLValidator.getInstance().validate(hsl);
    }

    /**
     * Passthrough method to validate an HSL array structure.
     */
    @Override
    public double[] toHSL(double[] color) throws ColorConversionException {
        return HSB_HSLValidator.getInstance().validate(color);
    }
}