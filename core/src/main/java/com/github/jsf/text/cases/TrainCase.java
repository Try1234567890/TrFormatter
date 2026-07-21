package com.github.jsf.text.cases;

import java.util.regex.Pattern;

public class TrainCase implements TextCase {
    public static final Pattern PATTERN = Pattern.compile("([a-zA-Z0-9]+-)+[a-zA-Z0-9]+");

    public static boolean isTrainCase(String text) {
        return PATTERN.matcher(text).matches();
    }

    @Override
    public String[] toSpaceCase(String text) {
        return getWords(text, '-');
    }

    @Override
    public String fromSpaceCase(String[] spaceCase) {
        StringBuilder sb = new StringBuilder();
        for (String word : spaceCase) {
            sb.append(word).append("-");
        }
        return (sb.isEmpty() ? sb : sb.substring(0, sb.length() - 1)).toString();
    }
}
