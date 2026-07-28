package com.github.jsf.color.validator;

import com.github.jsf.color.exceptions.InvalidColorException;
import com.github.utilities.validators.Preconditions;

public class DecimalValidator implements ColorValidator<Integer> {
    private DecimalValidator() {}

    private record Holder() {
        private static final DecimalValidator INSTANCE = new DecimalValidator();
    }

    public static DecimalValidator getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Checks if the {@code color} is a valid decimal color.
     * <p>
     * The performed checks are:
     *  - The color must be an integer
     *  - The color must be between 0 and FFFFFFFF
     *
     * @param color The color object to check.
     * @throws InvalidColorException if the color is not valid.
     */
    @Override
    public Integer validate(Integer color) throws InvalidColorException {
        Preconditions.parameterNotNull(color, "color");
        // Allows 0x00000000 through 0xFFFFFFFF
        if (!(Integer.compareUnsigned(color, 0) >= 0 &&
                Integer.compareUnsigned(color, 0xFFFFFFFF) <= 0)) {
            throw new InvalidColorException("The decimal color representation must be between 0 and FFFFFFFF");
        }

        return color;
    }
}
