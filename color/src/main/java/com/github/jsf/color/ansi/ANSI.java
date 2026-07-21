package com.github.jsf.color.ansi;

import com.github.jsf.color.converter.ARGBConverter;

public interface ANSI {

    /**
     * The reset (or close) tag of the ANSI colors.
     *
     * @see <a href="https://en.wikipedia.org/wiki/ANSI_escape_code">ANSI escape code</a>
     */
    String RESET_TAG = "\u001B[0m";

    /**
     * Retrieve the name of the color.
     * <p>
     * For {@link ANSI24Bit} the name is in the format "RGB[r, g, b]".
     *
     * @return The color name.
     */
    String getName();

    /**
     * Return the color code of the color.
     * <p>
     * This is the code used in the ANSI escape sequence.
     * For {@link ANSI24Bit} {@code 0} is returned.
     *
     * @return the color code.
     */
    int getCode();

    /**
     * Retrieve the red channel of the color.
     *
     * @return an integer between 0 and 255.
     */
    int getR();

    /**
     * Retrieve the green channel of the color.
     *
     * @return an integer between 0 and 255.
     */
    int getG();

    /**
     * Retrieve the blue channel of the color.
     *
     * @return an integer between 0 and 255.
     */
    int getB();

    /**
     * Retrieve the escape text sequence of this ANSI color.
     *
     * @return the escape text sequence.
     */
    String getTextEscapeSequence();

    /**
     * Retrieve the escape background sequence of this ANSI color.
     *
     * @return the escape background sequence.
     */
    String getBackgroundEscapeSequence();

    /**
     * Retrieve the ANSI color nearest to the provided {@code RGB values} from the {@code values}.
     *
     * @param rgb    The RGB values.
     * @param values The values to retrieve from
     * @param <T>    The values type.
     * @return The color nearest to the provided {@code RGB values}.
     */
    static <T extends ANSI> T getNearest(int[] rgb, T[] values) {
        int[] argb = ARGBConverter.ensureAlphaChannel(rgb);

        int dR = argb[1];
        int dG = argb[2];
        int dB = argb[3];

        T nearest = null;
        double minDistance = Double.MAX_VALUE;

        for (T color : values) {
            int r = color.getR();
            int g = color.getG();
            int b = color.getB();

            int rDiff = r - dR;
            int gDiff = g - dG;
            int bDiff = b - dB;

            double distance = (rDiff * rDiff) + (gDiff * gDiff) + (bDiff * bDiff);

            if (distance < minDistance) {
                minDistance = distance;
                nearest = color;
            }
        }

        return nearest;
    }
}
