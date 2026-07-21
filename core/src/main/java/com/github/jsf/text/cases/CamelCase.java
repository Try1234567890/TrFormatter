package com.github.jsf.text.cases;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class CamelCase implements TextCase {
    public static final Pattern PATTERN = Pattern.compile("[a-z][a-zA-Z0-9]+");

    public static boolean isCamelCase(String text) {
        if (text.isEmpty()) return false;
        return PATTERN.matcher(text).matches();
    }

    @Override
    public String[] toSpaceCase(String text) {
        if (text.isEmpty()) return new String[0];
        Set<String> words = new HashSet<>();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
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
        for (int i = 0; i < spaceCase.length; i++) {
            String word = spaceCase[i];
            sb.append(convertWord(word, i == 0));
        }
        return sb.toString();
    }

    private String convertWord(String word, boolean isFirst) {
        if (word.isEmpty()) return word;
        if (isFirst) return Character.toLowerCase(word.charAt(0)) + word.substring(1).toLowerCase();
        return Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
    }
}

















