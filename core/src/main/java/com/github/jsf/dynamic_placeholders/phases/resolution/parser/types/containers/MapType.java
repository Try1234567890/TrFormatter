package com.github.jsf.dynamic_placeholders.phases.resolution.parser.types.containers;

import com.github.jsf.dynamic_placeholders.names.UName;
import com.github.jsf.dynamic_placeholders.phases.resolution.parser.types.ParameterType;
import com.github.jsf.dynamic_placeholders.phases.resolution.scanner.beans.DPDelimiterSet;
import com.github.jsf.scanners.IllegalComponentException;
import com.github.jsf.scanners.components.IndexedComponent;
import com.github.jsf.scanners.delimiters.Delimiter;
import com.github.jsf.scanners.delimiters.IdentifierDelimiter;
import com.github.jsf.scanners.delimiters.StringDelimiter;
import com.github.jsf.scanners.impls.SplitterScanner;
import com.github.jsf.text.Text;
import java.util.*;

public class MapType extends ParameterType<Map<String, Object>> {
    public static final UName MAP_DELIMITER_ID = new UName("LIST_DELIMITER");
    public static final UName MAP_SPLITTER_ID = new UName("LIST_SPLITTER");
    public static final UName MAP_ASSIGNER_ID = new UName("LIST_ASSIGNER");

    public static final Delimiter MAP_DELIMITER_DEFAULT_VALUE = Delimiter.of("{", "}");
    public static final StringDelimiter MAP_SPLITTER_DEFAULT_VALUE = Delimiter.of(",");
    public static final StringDelimiter MAP_ASSIGNER_DEFAULT_VALUE = Delimiter.of("=");

    @Override
    protected Optional<Map<String, Object>> _is(Text str, DPDelimiterSet set) {
        Delimiter delimiter = set.retrieve(MAP_DELIMITER_ID).orElse(MAP_DELIMITER_DEFAULT_VALUE);

        if (str.startsWith(delimiter.open())
                && str.endsWith(delimiter.close())) {
            Text text = str.subtext(1, str.length() - 1);
            return Optional.of(parseValues(text, set));
        }

        return Optional.empty();
    }

    private Map<String, Object> parseValues(Text text, DPDelimiterSet set) {
        StringDelimiter splitter = getSplitter(set);
        Map<String, Object> parameters = new LinkedHashMap<>();
        List<IndexedComponent> components = new SplitterScanner(text, splitter).scanAll();

        for (IndexedComponent component : components) {
            Map.Entry<String, Object> entry = parseParameter(component.getComponent(), set);
            parameters.put(entry.getKey(), entry.getValue());
        }

        return parameters;
    }

    private Map.Entry<String, Object> parseParameter(Text component, DPDelimiterSet set) {
        StringDelimiter assigner = getAssigner(set);
        int assignerIndex = component.indexOfNonBetweenStrings(assigner.value());
        if (assignerIndex == -1)
            throw new IllegalComponentException("The parameter " + component + " is not assigner to any value with the correct delimiter: \"" + assigner + "\"");

        Text key = component.subtext(0, assignerIndex);
        Text value = component.subtextFrom(assignerIndex + assigner.value().length());
        return new AbstractMap.SimpleEntry<>(key.toString(), ParameterType.typize(value, set));
    }

    private StringDelimiter getSplitter(DPDelimiterSet set) {
        Delimiter delimiter = set.retrieve(MAP_SPLITTER_ID).orElse(MAP_SPLITTER_DEFAULT_VALUE);
        return delimiter instanceof StringDelimiter ? (StringDelimiter) delimiter : MAP_SPLITTER_DEFAULT_VALUE;
    }

    private StringDelimiter getAssigner(DPDelimiterSet set) {
        Delimiter delimiter = set.retrieve(MAP_ASSIGNER_ID).orElse(MAP_ASSIGNER_DEFAULT_VALUE);
        return delimiter instanceof StringDelimiter ? (StringDelimiter) delimiter : MAP_ASSIGNER_DEFAULT_VALUE;
    }
}














