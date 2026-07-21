package com.github.jsf.text.searches.delimiters;

import com.github.jsf.scanners.delimiters.Delimiter;

import java.util.ArrayList;
import java.util.List;

public sealed abstract class ReverseSearchWithDelimiters extends SearchWithDelimiters
        permits ReverseCharacterSearchWithDelimiters, ReverseSequenceSearchWithDelimiters {

    protected ReverseSearchWithDelimiters(char[] text, int offset, int length, Delimiter[] delimiters) {
        super(text, offset, length, delimiters);
    }

    protected abstract boolean matches(int offset);

    @Override
    public int search() {
        char[][] openDelimiters = openDelimiters(), closeDelimiters = closeDelimiters();
        int textLen = text().length;

        int offset = textLen - offset();
        int length = textLen - length();

        List<DelimiterPointer> pointers = new ArrayList<>(6);

        while (offset > length) {
            if (!pointers.isEmpty()) {
                DelimiterPointer pointer = matchesOpen(pointers, offset);

                if (pointer != null) {
                    pointer.minusDepth();

                    if (pointer.depth() == 0) {
                        pointers.remove(pointer);
                    }

                    offset -= pointer.openDelimiter().length;
                    continue;
                }
            }

            int closeIndex;
            if ((closeIndex = matchesAny(closeDelimiters, offset)) != -1) {
                char[] openDel = openDelimiters[closeIndex];
                char[] closeDel = closeDelimiters[closeIndex];
                handlePointer(pointers, openDel, closeDel);
                offset -= closeDel.length;
            } else if (pointers.isEmpty()) {
                if (matches(offset)) {
                    return offset;
                } else {
                    offset--;
                }
            } else {
                offset--;
            }
        }

        return -1;
    }

    private DelimiterPointer matchesOpen(List<DelimiterPointer> pointers, int offset) {
        for (int i = pointers.size() - 1; i >= 0; i--) {
            DelimiterPointer pointer = pointers.get(i);
            if (matches(pointer.openDelimiter(), offset)) {
                return pointer;
            }
        }
        return null;
    }
}