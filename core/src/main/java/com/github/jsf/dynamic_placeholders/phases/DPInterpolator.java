package com.github.jsf.dynamic_placeholders.phases;

import com.github.jsf.dynamic_placeholders.components.DynamicPlaceholder;
import com.github.jsf.text.Text;

import java.util.List;

public class DPInterpolator {
    private final Text text;
    private final List<DynamicPlaceholder> placeholders;

    public DPInterpolator(Text text, List<DynamicPlaceholder> placeholders) {
        this.text = text;
        this.placeholders = placeholders;
    }


    public Text interpolate() {
        StringBuilder sb = new StringBuilder(text.toString());

        for (int i = (placeholders.size() - 1); i >= 0; i--) {
            DynamicPlaceholder placeholder = placeholders.get(i);
            String result = placeholder.getResult();

            sb.replace(placeholder.start(), placeholder.end(), result);
        }

        return Text.of(sb);
    }
}
