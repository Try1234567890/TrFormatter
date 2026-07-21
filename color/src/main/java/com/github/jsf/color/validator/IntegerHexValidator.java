package com.github.jsf.color.validator;

import com.github.jsf.color.exceptions.InvalidColorException;
import com.github.utilities.validators.Preconditions;

public class IntegerHexValidator implements ColorValidator<Integer> {

    private IntegerHexValidator() {
    }

    private record Holder() {
        private static final IntegerHexValidator INSTANCE = new IntegerHexValidator();
    }

    public static IntegerHexValidator getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Checks if the {@code hex} string representation is a valid hex color.
     * The integer is converter to its hex string and provided to {@link StringHexValidator}.
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
     * @see StringHexValidator
     */
    @Override
    public Integer validate(Integer hex) throws InvalidColorException {
        Preconditions.parameterNotNull(hex, "hex");
        StringHexValidator.getInstance().validate(Integer.toHexString(hex));
        return hex;
    }
}
