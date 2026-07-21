package com.github.jsf.text.cases;

import java.util.Arrays;

public class UnknownCase implements TextCase {

    @Override
    public String[] toSpaceCase(String text) {
        // Split text by ALL space characters.
        return Arrays.stream(text.split("\\s+")).map(String::toLowerCase).toArray(String[]::new);
    }

    @Override
    public String fromSpaceCase(String[] spaceCase) {
        return String.join(" ", spaceCase);
    }
}
