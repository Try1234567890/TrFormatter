
package com.github.jsf.dynamic_placeholders.phases.resolution.parser.types.containers;

import com.github.jsf.dynamic_placeholders.names.UName;
import com.github.jsf.dynamic_placeholders.phases.resolution.parser.types.ParameterType;
import com.github.jsf.dynamic_placeholders.phases.resolution.scanner.beans.DPDelimiterSet;
import com.github.jsf.scanners.beans.IndexedComponent;
import com.github.jsf.scanners.delimiters.Delimiter;
import com.github.jsf.scanners.delimiters.StringDelimiter;
import com.github.jsf.scanners.impls.SplitterScanner;
import com.github.jsf.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ListType extends ParameterType<List<Object>> {
    public static final UName LIST_DELIMITER_ID = new UName("LIST_DELIMITER");
    public static final UName LIST_SPLITTER_ID = new UName("LIST_SPLITTER");

    public static final Delimiter LIST_DELIMITER_DEFAULT_VALUE = Delimiter.of("[", "]");
    public static final StringDelimiter LIST_SPLITTER_DEFAULT_VALUE = Delimiter.of(",");

    @Override
    protected Optional<List<Object>> _is(Text str, DPDelimiterSet set) {
        Delimiter delimiter = set.retrieve(LIST_DELIMITER_ID).orElse(LIST_DELIMITER_DEFAULT_VALUE);

        if (str.startsWith(delimiter.open())
                && str.endsWith(delimiter.close())) {
            Text text = str.subtext(1, str.length() - 1);
            return Optional.of(parseValues(text, set));
        }

        return Optional.empty();
    }

    private List<Object> parseValues(Text text, DPDelimiterSet set) {
        StringDelimiter splitter = getSplitter(set);
        List<Object> objects = new ArrayList<>();
        List<IndexedComponent> components = new SplitterScanner(text, splitter).scanAll();

        for (IndexedComponent component : components) {
            Text componentText = component.getComponent();
            objects.add(ParameterType.typize(componentText, set));
        }

        return objects;
    }

    private StringDelimiter getSplitter(DPDelimiterSet set) {
        Delimiter delimiter = set.retrieve(LIST_SPLITTER_ID).orElse(LIST_SPLITTER_DEFAULT_VALUE);
        return delimiter instanceof StringDelimiter ? (StringDelimiter) delimiter : LIST_SPLITTER_DEFAULT_VALUE;
    }
}














