package com.github.jsf.dynamic_placeholders.phases.resolution.lexer.tokens;

public enum TokenType {
    IDENTIFIER(),

    OPEN_PLACEHOLDER(),
    OPEN_ACTION(),
    OPEN_CONDITION(),
    OPEN_FUNCTION(),

    CLOSE_PLACEHOLDER(),
    CLOSE_ACTION(),
    CLOSE_CONDITION(),
    CLOSE_FUNCTION(),

    OPEN_PARAMS(),
    CLOSE_PARAMS(),
    SPLIT_PARAMS(),
    ASSIGN_PARAM(),
    PARAM_VALUE()

}
