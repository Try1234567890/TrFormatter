package com.github.jsf.color.validator;

import com.github.jsf.color.exceptions.InvalidColorException;
import com.github.utilities.validators.Preconditions;

public class HSB_HSLValidator implements ColorValidator<double[]> {
    private HSB_HSLValidator() {
    }

    private record Holder() {
        private static final HSB_HSLValidator INSTANCE = new HSB_HSLValidator();
    }

    public static HSB_HSLValidator getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Checks if the {@code color} is a valid HSB (or an HSL) valid color representation.
     * <p>
     * The performed checks are:
     *  - The length of the array must be 3
     *  - All channels must be valid
     * <p>
     * The HSB and HSL color models follow the same validation rules.
     *
     *
     * @param color the color to validate.
     * @return The color if it is valid.
     * @throws InvalidColorException If the color is not valid.
     */
    @Override
    public double[] validate(double[] color) throws InvalidColorException {
        Preconditions.parameterNotNull(color, "color");
        if (color.length != 3) {
            throw new InvalidColorException("The HSB color must be an array of 3 integers. Length: " + color.length);
        }

        double h = color[0];
        double s = color[1];
        double b = color[2];

        if (h < 0.0 || h > 360.0) {
            throw new InvalidColorException("The hue channel is < than 0 or > 360. Hue channel: " + h);
        }
        if (s < 0.0 || s > 1.0) {
            throw new InvalidColorException("The saturation channel is < than 0 or > 1. Saturation channel: " + s);
        }
        if (b < 0.0 || b > 1.0) {
            throw new InvalidColorException("The brightness channel is < than 0 or > 1. Brightness channel: " + b);
        }

        return color;
    }
}
