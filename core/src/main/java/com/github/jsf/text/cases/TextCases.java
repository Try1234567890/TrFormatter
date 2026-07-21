package com.github.jsf.text.cases;

import com.github.utilities.registries.Registry;

import java.util.function.Predicate;

public class TextCases extends Registry<Predicate<String>, TextCase> {
    private TextCases() {
        register(AlternateCase::isAlternateCase, new AlternateCase());
        register(CamelCase::isCamelCase, new CamelCase());
        register(DotCase::isDotCase, new DotCase());
        register(PascalCase::isPascalCase, new PascalCase());
        register(SnakeCase::isSnakeCase, new SnakeCase());
        register(TrainCase::isTrainCase, new TrainCase());
    }

    private record Holder() {
        private static final TextCases INSTANCE = new TextCases();
    }

    public static TextCases getInstance() {
        return Holder.INSTANCE;
    }


}
