package com.github.jsf.text.cases;

import com.github.utilities.validators.Preconditions;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public interface TextCase {

    static boolean areEquals(TextCase one, TextCase two) {
        return Preconditions.simpleParameterNotNull(one, "one").getClass()
                .equals(Preconditions.simpleParameterNotNull(two, "two").getClass());
    }

    static TextCase of(String text) {
        for (Map.Entry<Predicate<String>, TextCase> textCase : TextCases.getInstance().entries()) {
            Predicate<String> predicate = textCase.getKey();
            if (predicate.test(text))
                return textCase.getValue();
        }
        return new UnknownCase();
    }


    String[] toSpaceCase(String text);

    default String fromSpaceCase(String text) {
        TextCase textCase = of(text);
        return fromSpaceCase(textCase.toSpaceCase(text));
    }

    String fromSpaceCase(String[] spaceCase);

    default String[] getWords(@NotNull String text, char delimiter) {
        if (text.isEmpty()) return new String[0];
        List<String> words = new ArrayList<>();
        int tail = -1, head;
        while ((tail = text.indexOf(delimiter, head = tail + 1)) != -1) {
            String word = text.substring(head, tail);
            words.add(word.toLowerCase());
        }

        if (head < text.length()) {
            String word = text.substring(head);
            words.add(word.toLowerCase());
        }

        return words.toArray(String[]::new);
    }
}
