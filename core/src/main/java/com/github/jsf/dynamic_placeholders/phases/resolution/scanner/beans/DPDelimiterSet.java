package com.github.jsf.dynamic_placeholders.phases.resolution.scanner.beans;

import com.github.jsf.dynamic_placeholders.names.UName;
import com.github.jsf.dynamic_placeholders.phases.resolution.parser.types.containers.ListType;
import com.github.jsf.dynamic_placeholders.phases.resolution.parser.types.containers.MapType;
import com.github.jsf.dynamic_placeholders.phases.resolution.parser.types.others.colors.ARGBColorType;
import com.github.jsf.dynamic_placeholders.phases.resolution.parser.types.others.colors.HexColorType;
import com.github.jsf.scanners.delimiters.*;
import com.github.utilities.validators.Preconditions;

public class DPDelimiterSet extends DelimiterSet {
    public static final UName PLACEHOLDER_ID = new UName("PLACEHOLDER");
    public static final UName ACTION_ID = new UName("ACTION");
    public static final UName CONDITION_ID = new UName("CONDITION");
    public static final UName FUNCTION_ID = new UName("FUNCTION");
    public static final UName PARAMS_ID = new UName("PARAMS");
    public static final UName PARAMS_SPLITTER_ID = new UName("PARAMS_SPLITTER");
    public static final UName PARAMS_ASSIGNER_ID = new UName("PARAMS_ASSIGNER");

    public static final ComponentDelimiter PLACEHOLDER_DEFAULT_VALUE = Delimiter.of("{", "}");
    public static final ComponentDelimiter ACTION_DEFAULT_VALUE = Delimiter.of("#[", "]");
    public static final ComponentDelimiter CONDITION_DEFAULT_VALUE = Delimiter.of("@[", "]");
    public static final ComponentDelimiter FUNCTION_DEFAULT_VALUE = Delimiter.of("/[", "]");
    public static final ComponentDelimiter PARAMS_DEFAULT_VALUE = Delimiter.of("(", ")");
    public static final StringDelimiter PARAMS_SPLITTER_DEFAULT_VALUE = Delimiter.of(",");
    public static final StringDelimiter PARAMS_ASSIGNER_DEFAULT_VALUE = Delimiter.of("=");

    public static final DPDelimiterSet DEFAULT = (DPDelimiterSet) new DPDelimiterSet()
            .newDelimiter(PLACEHOLDER_ID, PLACEHOLDER_DEFAULT_VALUE)
            .newDelimiter(ACTION_ID, ACTION_DEFAULT_VALUE)
            .newDelimiter(CONDITION_ID, CONDITION_DEFAULT_VALUE)
            .newDelimiter(FUNCTION_ID, FUNCTION_DEFAULT_VALUE)
            .newDelimiter(PARAMS_ID, PARAMS_DEFAULT_VALUE)
            .newDelimiter(PARAMS_SPLITTER_ID, PARAMS_SPLITTER_DEFAULT_VALUE)
            .newDelimiter(PARAMS_ASSIGNER_ID, PARAMS_ASSIGNER_DEFAULT_VALUE)
            .newDelimiter(ListType.LIST_DELIMITER_ID, ListType.LIST_DELIMITER_DEFAULT_VALUE)
            .newDelimiter(ListType.LIST_SPLITTER_ID, ListType.LIST_SPLITTER_DEFAULT_VALUE)
            .newDelimiter(MapType.MAP_DELIMITER_ID, MapType.MAP_DELIMITER_DEFAULT_VALUE)
            .newDelimiter(MapType.MAP_SPLITTER_ID, MapType.MAP_SPLITTER_DEFAULT_VALUE)
            .newDelimiter(MapType.MAP_ASSIGNER_ID, MapType.MAP_ASSIGNER_DEFAULT_VALUE)
            .newDelimiter(HexColorType.HEX_DELIMITER_IDENTIFIER, HexColorType.HEX_DELIMITER_DEFAULT_VALUE)
            .newDelimiter(ARGBColorType.ARGB_DELIMITER_IDENTIFIER, ARGBColorType.ARGB_DELIMITER_DEFAULT_VALUE);


    public DPDelimiterSet() {
    }

    public DPDelimiterSet forPlaceholders(Delimiter delimiter) {
        register(PLACEHOLDER_ID, Preconditions.parameterNotNull(delimiter, "delimiter"));
        return this;
    }

    public Delimiter getPlaceholders() {
        return retrieve(PLACEHOLDER_ID).orElseThrow(() -> new IllegalDelimiterException("This set of delimiters " + this +
                " doesn't contains the placeholder delimiter with ID " + PLACEHOLDER_ID));
    }

    public DPDelimiterSet forFunctions(Delimiter delimiter) {
        register(FUNCTION_ID, Preconditions.parameterNotNull(delimiter, "delimiter"));
        return this;
    }

    public Delimiter getFunctions() {
        return retrieve(FUNCTION_ID).orElseThrow(() -> new IllegalDelimiterException("This set of delimiters " + this +
                " doesn't contains the function delimiter with ID " + FUNCTION_ID));
    }

    public DPDelimiterSet forConditions(Delimiter delimiter) {
        register(CONDITION_ID, Preconditions.parameterNotNull(delimiter, "delimiter"));
        return this;
    }

    public Delimiter getConditions() {
        return retrieve(CONDITION_ID).orElseThrow(() -> new IllegalDelimiterException("This set of delimiters " + this +
                " doesn't contains the condition delimiter with ID " + CONDITION_ID));
    }

    public DPDelimiterSet forActions(Delimiter delimiter) {
        register(ACTION_ID, Preconditions.parameterNotNull(delimiter, "delimiter"));
        return this;
    }

    public Delimiter getActions() {
        return retrieve(ACTION_ID).orElseThrow(() -> new IllegalDelimiterException("This set of delimiters " + this +
                " doesn't contains the action delimiter with ID " + ACTION_ID));
    }

    public DPDelimiterSet forParams(Delimiter delimiter) {
        register(PARAMS_ID, Preconditions.parameterNotNull(delimiter, "delimiter"));
        return this;
    }

    public Delimiter getParams() {
        return retrieve(PARAMS_ID).orElseThrow(() -> new IllegalDelimiterException("This set of delimiters " + this +
                " doesn't contains the params delimiter with ID " + PARAMS_ID));
    }

    public DPDelimiterSet forParamsSplitter(StringDelimiter delimiter) {
        register(PARAMS_SPLITTER_ID, Preconditions.parameterNotNull(delimiter, "delimiter"));
        return this;
    }

    public StringDelimiter getParamsSplitter() {
        return retrieve(PARAMS_SPLITTER_ID)
                .filter(del -> del instanceof StringDelimiter)
                .map(del -> (StringDelimiter) del).orElseThrow(() -> new IllegalDelimiterException("This set of delimiters " + this +
                        " doesn't contains the params splitter delimiter with ID " + PARAMS_SPLITTER_ID + " or is not a StringDelimiter"));
    }

    public DPDelimiterSet forParamsAssigner(StringDelimiter delimiter) {
        register(PARAMS_ASSIGNER_ID, Preconditions.parameterNotNull(delimiter, "delimiter"));
        return this;
    }

    public StringDelimiter getParamsAssigner() {
        return retrieve(PARAMS_ASSIGNER_ID)
                .filter(del -> del instanceof StringDelimiter)
                .map(del -> (StringDelimiter) del).orElseThrow(() -> new IllegalDelimiterException("This set of delimiters " + this +
                        " doesn't contains the params assigner delimiter with ID " + PARAMS_ASSIGNER_ID + " or is not a StringDelimiter"));
    }
}











































