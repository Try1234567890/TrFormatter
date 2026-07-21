package com.github.jsf.color.validator;

import com.github.jsf.color.exceptions.InvalidColorException;
import com.github.utilities.validators.Preconditions;

public class  StringHexValidator implements ColorValidator<String> {
    // Include only the upper case letter because during the normalization the string is upper-cased.
    private static final String VALID_CHARS = "0123456789ABCDEF";

    private StringHexValidator() {
    }

    private record Holder() {
        private static final StringHexValidator INSTANCE = new StringHexValidator();
    }

    public static StringHexValidator getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Checks if the {@code hex} string representation is a valid hex color.
     * <p>
     * The performed checks are:
     * - The length of the string must be 6 or 8 characters
     * - All characters must be valid hex characters
     * <p>
     * Automatically removes any prefix like {@code #} or {@code 0x}.
     *
     * @param hex The hex representation of the color.
     * @return The normalized hex color.
     * @throws InvalidColorException If the hex is not valid.
     */
    @Override
    public String validate(String hex) throws InvalidColorException {
        Preconditions.parameterNotNull(hex, "hex");
        String color = normalize(hex);
        int length = color.length();

        // AA_RR_GG_BB = 8 characters
        // RR_GG_BB = 6 characters
        if (length != 6 && length != 8) {
            // Cannot be a valid hex
            throw new InvalidColorException("The hex color representation must have 6 or 8 characters. Color provided: " + hex);
        }

        for (int i = 0; i < length; i++) {
            char ch = color.charAt(i);
            if (VALID_CHARS.indexOf(ch) == -1)
                // The character is not a valid hex character
                throw new InvalidColorException("The hex color representation must contain only valid hex characters. Color provided: " + hex + " contains " + ch);
        }

        return color;
    }

    /**
     * Normalize the hex string by removing the prefix like {@code #} or {@code 0x}
     * and upper-casing the string.
     *
     * @param hex the hex string representation.
     * @return the normalized hex string.
     */
    public static String normalize(String hex) {
        if (hex.startsWith("#")) return hex.substring(1).toUpperCase();
        if (hex.startsWith("0x") || hex.startsWith("0X")) return hex.substring(2).toUpperCase();
        return hex.toUpperCase();
    }

}
