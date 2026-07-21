package com.github.jsf.scanners;

import com.github.jsf.scanners.delimiters.Delimiter;
import com.github.utilities.options.SetOption;
import com.github.utilities.validators.Preconditions;

import java.util.HashSet;
import java.util.Set;

public class ScannerOptions {
    /**
     * This options allow to define some {@link Delimiter} to exclude all
     * text inside one of them from the search.
     * <p>
     * By default, all the text inside the quotes ({@code "}  or {@code '}) is excluded.
     */
    public final SetOption<Delimiter> EXCLUDERS = new SetOption<>(new HashSet<>(Set.of(Delimiter.of("\""), Delimiter.of("'"))));


    public ScannerOptions newExcluder(Delimiter delimiter) {
        EXCLUDERS.add(Preconditions.parameterNotNull(delimiter, "delimiter"));
        return this;
    }
}
