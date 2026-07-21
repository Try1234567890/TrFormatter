package com.github.jsf.text.cases;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class PascalCase implements TextCase {
    public static final Pattern PATTERN = Pattern.compile("([A-Z][a-z0-9]+)+");

    public static boolean isPascalCase(String text) {
        return PATTERN.matcher(text).matches();
    }

    @Override
    public String[] toSpaceCase(String text) {
        if (text.isEmpty()) return new String[0];
        Set<String> words = new HashSet<>();
        StringBuilder builder = new StringBuilder().append(Character.toLowerCase(text.charAt(0)));

        for (int i = 1; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (Character.isUpperCase(ch)) {
                words.add(builder.toString());
                builder.setLength(0);
                builder.append(Character.toLowerCase(ch));
            } else builder.append(ch);
        }

        words.add(builder.toString());
        return words.toArray(String[]::new);
    }

    @Override
    public String fromSpaceCase(String[] spaceCase) {
        StringBuilder sb = new StringBuilder();
        for (String word : spaceCase) {
            sb.append(convertWord(word));
        }
        return sb.toString();
    }

    private String convertWord(String word) {
        if (word.isEmpty()) return word;

        return Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
    }
}
