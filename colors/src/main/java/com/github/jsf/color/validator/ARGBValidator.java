package com.github.jsf.color.validator;

import com.github.jsf.color.exceptions.InvalidColorException;
import com.github.utilities.validators.Preconditions;

public class ARGBValidator implements ColorValidator<int[]> {

    private ARGBValidator() {
    }

    private record Holder() {
        private static final ARGBValidator INSTANCE = new ARGBValidator();
    }

    public static ARGBValidator getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Checks if the {@code color} is a valid representation of a ARGB color.
     * <p>
     * The performed checks are:
     * - The color must be an array of 3 or 4 integers
     * - All the integers must be between 0 and 255
     *
     * @param color The color to check.
     * @throws InvalidColorException if the color is not valid.
     */
    @Override
    public int[] validate(int[] color) throws InvalidColorException {
        Preconditions.parameterNotNull(color, "color");
        // [AA, RR, GG, BB] = 4 length
        if (color.length == 4) {
            int a = color[0];
            int r = color[1];
            int g = color[2];
            int b = color[3];

            if (a < 0 || a > 255) {
                throw new InvalidColorException("The Alpha channel is < than 0 or > 255. Alpha channel: " + a);
            }

            areRGBChannelsValid(r, g, b);
        }

        // [RR, GG, BB] = 3 length
        if (color.length == 3) {
            int r = color[0];
            int g = color[1];
            int b = color[2];

            areRGBChannelsValid(r, g, b);
        }

        return color;
    }

    /**
     * Checks if the {@code r}, {@code g} and {@code b} channels are valid.
     * A channel considered valid if it is between 0 and 255.
     *
     * @param r the red channel of a color
     * @param g the green channel of a color
     * @param b the blue channel of a color
     * @throws InvalidColorException if the channels are not valid.
     */

    private void areRGBChannelsValid(int r, int g, int b) throws InvalidColorException {

       if (r < 0 || r > 255) {

           throw new InvalidColorException("The Red channel is < than 0 or > 255. Red channel: " + r);

       }

       if (g < 0 || g > 255) {

           throw new InvalidColorException("The Green channel is < than 0 or > 255. Green channel: " + g);

       }

       if (b < 0 || b > 255) {

           throw new InvalidColorException("The Blue channel is < than 0 or > 255. Blue channel: " + b);

       }

    }
}
