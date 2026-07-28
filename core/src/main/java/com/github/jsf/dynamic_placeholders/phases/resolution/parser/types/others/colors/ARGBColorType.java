package com.github.jsf.dynamic_placeholders.phases.resolution.parser.types.others.colors;

import com.github.jsf.color.Color;
import com.github.jsf.dynamic_placeholders.names.UName;
import com.github.jsf.dynamic_placeholders.phases.resolution.scanner.beans.DPDelimiterSet;
import com.github.jsf.scanners.delimiters.ComponentDelimiter;
import com.github.jsf.scanners.delimiters.Delimiter;
import com.github.jsf.text.Text;

import java.util.Optional;

public class ARGBColorType extends ColorType {
    public static final UName ARGB_DELIMITER_IDENTIFIER = new UName("ARGB_DELIMITER");
    public static final ComponentDelimiter ARGB_DELIMITER_DEFAULT_VALUE = Delimiter.of("argb:[", "]");

    @Override
    protected Optional<Color> _is(Text str, DPDelimiterSet set) {
        ComponentDelimiter delimiter = getDelimiter(set);

        if (seamsARGB(str, delimiter)) {
            Color color = Color.ofARGB(getARGBChannels(str, delimiter));
            return Optional.of(color);
        }
        return Optional.empty();
    }

    private int[] getARGBChannels(Text str, ComponentDelimiter delimiter) {
        int[] channels = new int[4];
        Text value = str.subtext(delimiter.open().length(), (str.length() - delimiter.close().length()));
        Text[] rawChannels = value.split(",");

        if (rawChannels.length != 4) {
            throw new IllegalArgumentException("Invalid ARGB format: \"" + str + "\". The expected format is: " + delimiter.open() + "a,r,g,b" + delimiter.close());
        }

        for (int i = 0; i < 4; i++) {
            channels[i] = parseChannel(rawChannels[i]);
        }
        return channels;
    }

    private byte parseChannel(Text rawChannel) {
        try {
            return Byte.parseByte(rawChannel.toString());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid color channel value: " + rawChannel);
        }
    }

    private boolean seamsARGB(Text str, ComponentDelimiter delimiter) {
        return str.startsWith(delimiter.open()) && str.endsWith(delimiter.close());
    }

    private ComponentDelimiter getDelimiter(DPDelimiterSet set) {
        return set.retrieve(ARGB_DELIMITER_IDENTIFIER)
                .filter(del -> del instanceof ComponentDelimiter)
                .map(del -> (ComponentDelimiter) del)
                .orElse(ARGB_DELIMITER_DEFAULT_VALUE);
    }

}
