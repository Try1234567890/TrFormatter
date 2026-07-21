package com.github.jsf.color.validator;


import com.github.jsf.color.exceptions.InvalidColorException;

public interface ColorValidator<T> {

    /**
     * Validate the color.
     *
     * @param color the color
     * @return the validated color
     * @throws InvalidColorException if the color is not valid.
     */
    T validate(T color) throws InvalidColorException;

}
