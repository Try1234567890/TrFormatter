package com.github.jsf.dynamic_placeholders.components.impls.actions;

import com.github.jsf.dynamic_placeholders.components.ComponentsInfo;
import com.github.jsf.dynamic_placeholders.names.UName;

import java.util.Random;

public class RandomChoice extends Action {
    private static final Random RANDOM = new Random();
    public static final UName ID = new UName("random_choice", "choice", "pick");
    public static final UName OPTIONS = new UName("options", "opt", "list");
    public static final UName DELIMITER = new UName("delimiter", "delim", "d");

    public RandomChoice(ComponentsInfo infos) {
        super(ID, infos);
    }

    @Override
    public String evaluate() {
        String rawOptions = as(OPTIONS, String.class).orElseThrow(() ->
                new IllegalArgumentException("The parameter " + OPTIONS + " is needed for \"" + ID + "\" action"));
        String delimiter = as(DELIMITER, String.class).orElse(",");
        
        String[] choices = rawOptions.split(delimiter);
        if (choices.length == 0) return "";
        
        int randomIndex = RANDOM.nextInt(choices.length);
        return choices[randomIndex].trim();
    }
}