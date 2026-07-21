package com.github.jsf.scanners.delimiters;

import com.github.jsf.dynamic_placeholders.names.UName;
import com.github.utilities.registries.Registry;

import java.util.*;

public class DelimiterSet extends Registry<UName, Delimiter> {

    public DelimiterSet(Map<UName, Delimiter> delimiters) {
        super(delimiters);
    }

    public DelimiterSet() {
        this(new HashMap<>());
    }

    public DelimiterSet newDelimiter(UName name, Delimiter delimiter) {
        register(name, delimiter);
        return this;
    }

    public Optional<Delimiter> getDelimiter(UName name) {
        return streamEntries()
                .filter((entry) -> entry.getKey().equals(name))
                .findFirst()
                .map(Map.Entry::getValue);
    }

    public boolean contains(UName name) {
        return internalMap().containsKey(name);
    }

    public Optional<Delimiter> getDelimiter(String name) {
        return streamEntries()
                .filter((entry) -> entry.getKey().is(name))
                .findFirst()
                .map(Map.Entry::getValue);
    }

    public boolean contains(String name) {
        return streamEntries().anyMatch((entry) -> entry.getKey().is(name));
    }

    public Set<UName> getNames() {
        return keys();
    }

    public Collection<Delimiter> getDelimiters() {
        return values();
    }
}
