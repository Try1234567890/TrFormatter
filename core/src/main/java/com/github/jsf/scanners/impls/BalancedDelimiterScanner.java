package com.github.jsf.scanners.impls;

import com.github.jsf.scanners.ScannerOptions;
import com.github.jsf.scanners.delimiters.Delimiter;
import com.github.jsf.scanners.IllegalComponentException;
import com.github.jsf.scanners.components.IndexedComponent;
import com.github.jsf.text.Text;

import java.util.Optional;

public class BalancedDelimiterScanner extends DelimiterScanner {

    public BalancedDelimiterScanner(Text text, ScannerOptions options, Delimiter delimiter) throws NullPointerException, IllegalArgumentException {
        super(text, options, delimiter);
    }

    public BalancedDelimiterScanner(Text text, Delimiter delimiter) throws NullPointerException, IllegalArgumentException {
        super(text, delimiter);
    }

    @Override
    public Optional<IndexedComponent> scanFirst(int offset, int length) throws IllegalComponentException {
        Delimiter[] excluders = options().EXCLUDERS.toArray(new Delimiter[0]);
        Text text = text();
        String open = getDelimiter().open(), close = getDelimiter().close();
        int openLen = open.length(), closeLen = close.length();
        int depth = 0, outerOpenDelIndex = -1, index = offset;

        while (index < length) {
            int openIndex = text.indexOfNonBetween(open, index, length, excluders);
            int closeIndex = text.indexOfNonBetween(close, index, length, excluders);
            if (openIndex == -1 && closeIndex == -1) break;

            if (openIndex != -1 && (closeIndex == -1 || openIndex < closeIndex)) {
                if (outerOpenDelIndex == -1) {
                    outerOpenDelIndex = openIndex;
                }
                depth++;
                index = openIndex + openLen;
            } else {
                depth--;

                if (depth < 0) {
                    throw new IllegalComponentException("At the index " + closeIndex + " near " + getContext(closeIndex) +
                            " there is a closing delimiter (\"" + close + "\") with no matching opening delimiter (\"" + open + "\").");
                }

                if (depth == 0) {
                    int endIndex = closeIndex + closeLen;
                    Text component = text.subtext(outerOpenDelIndex, endIndex);
                    return Optional.of(new IndexedComponent(component, outerOpenDelIndex, endIndex));
                }

                index = closeIndex + closeLen;
            }
        }

        if (depth > 0) {
            throw new IllegalComponentException("At the index " + outerOpenDelIndex + " near " + getContext(outerOpenDelIndex) +
                    " there is an opening delimiter (\"" + open + "\") with no matching closing delimiter (\"" + close + "\").");
        }

        return Optional.empty();
    }

    @Override
    public Optional<IndexedComponent> scanLast(int offset, int length) throws IllegalComponentException {
        Text text = text();
        String open = getDelimiter().open(), close = getDelimiter().close();
        int textLen = text.length(), closeLen = close.length();
        int depth = 0, outerCloseDelIndex = -1, index = offset;

        while (index < length) {
            int closeIndex = text.lastIndexOfNonBetween(close, index, length, options().EXCLUDERS.toArray(new Delimiter[0]));
            int openIndex = text.lastIndexOfNonBetween(open, index, length, options().EXCLUDERS.toArray(new Delimiter[0]));
            if (openIndex == -1 && closeIndex == -1) break;

            if (closeIndex != -1 && (openIndex == -1 || closeIndex > openIndex)) { // We found a close delimiter before or without an open delimiter
                if (outerCloseDelIndex == -1) outerCloseDelIndex = (closeIndex + closeLen);
                depth++;
                index = (textLen - closeIndex); // The close delimiter length is already included.
            } else {
                depth--;

                if (depth < 0) {
                    throw new IllegalComponentException("At the index " + openIndex + " near " + getContext(openIndex) +
                            " there is a opening delimiter (\"" + open + "\") with no matching closing delimiter (\"" + close + "\").");
                }

                if (depth == 0) {
                    Text component = text.subtext(openIndex, outerCloseDelIndex);
                    return Optional.of(new IndexedComponent(component, openIndex, outerCloseDelIndex));
                }

                index = (textLen - openIndex);
            }
        }

        if (depth > 0) {
            throw new IllegalComponentException("At the index " + outerCloseDelIndex + " near " + getContext(outerCloseDelIndex) +
                    " there is an closing delimiter (\"" + close + "\") with no matching opening delimiter (\"" + open + "\").");
        }

        return Optional.empty();
    }
}

















