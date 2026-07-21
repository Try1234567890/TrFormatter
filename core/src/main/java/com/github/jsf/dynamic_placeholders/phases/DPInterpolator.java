package com.github.jsf.dynamic_placeholders.phases;

import com.github.jsf.dynamic_placeholders.phases.resolution.scanner.beans.IndexedDPComponent;
import com.github.jsf.text.Text;
import com.github.utilities.validators.Preconditions;

import java.util.List;

public class DPInterpolator {
    private final Text text;
    private final List<IndexedDPComponent> placeholders;
    private final List<String> results;

    public DPInterpolator(Text text,
                          List<IndexedDPComponent> placeholders,
                          List<String> results) {
        this.text = Preconditions.parameterNotNull(text, "text");
        Preconditions.check(placeholders.size() == results.size(), "The number of placeholders must be equal to the number of results");
        this.placeholders = Preconditions.parameterNotNull(placeholders, "placeholders");
        this.results = Preconditions.parameterNotNull(results, "results");
    }

    public Text interpolate() {
        StringBuilder sb = new StringBuilder(text.toString());

        for (int i = (placeholders.size() - 1); i >= 0; i--) {
            IndexedDPComponent placeholder = placeholders.get(i);
            String result = results.get(i);

            sb.replace(placeholder.start(), placeholder.end(), result);
        }

        return Text.of(sb);
    }
}
