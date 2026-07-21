package com.github.jsf.text.searches.delimiters;

import com.github.jsf.scanners.delimiters.Delimiter;

import java.util.ArrayList;
import java.util.List;

public sealed abstract class LinearSearchWithDelimiters extends SearchWithDelimiters
        permits LinearCharacterSearchWithDelimiters, LinearSequenceSearchWithDelimiters {

    protected LinearSearchWithDelimiters(char[] text, int offset, int length, Delimiter[] delimiters) {
        super(text, offset, length, delimiters);
    }

    protected abstract boolean matches(int offset);

    @Override
    public int search() {
        char[][] openDelimiters = openDelimiters(), closeDelimiters = closeDelimiters();
        int offset = offset(), length = length();
        List<DelimiterPointer> pointers = new ArrayList<>(6);

        while (offset < length) { //  Ensure that doesn't overflow
            if (!pointers.isEmpty()) { // If there is any delimiter to close
                DelimiterPointer pointer = matchesClose(pointers, offset);

                if (pointer != null) { // If we found a close delimiter
                    pointer.minusDepth(); // Reduce the depth

                    if (pointer.depth() == 0) {
                        // Remove pointer to the delimiter if the depth is 0
                        pointers.remove(pointer);
                    }

                    offset += pointer.closeDelimiter().length; // Move offset past the open delimiter.
                    continue;
                }
            }

            int openIndex;
            if ((openIndex = matchesAny(openDelimiters, offset)) != -1) { // If we have found an open delimiter
                char[] openDel = openDelimiters[openIndex];
                char[] closeDel = closeDelimiters[openIndex];
                handlePointer(pointers, openDel, closeDel);

                offset += openDel.length; // Move offset past the close delimiter.
            } else if (pointers.isEmpty()) { // If we are not inside any delimiter
                if (matches(offset)) { // If we have found the sequence
                    return offset; // Return the offset
                } else offset++; // Otherwise, if we haven't found the sequence, move to the next index
            } else offset++; // Otherwise, if we are inside a delimiter, move to the next index
        }

        return -1; // We have not found the sequence outside any delimiter
    }

    private DelimiterPointer matchesClose(List<DelimiterPointer> pointers, int offset) {
        // We loop throght the pointers in reverse because, MOST OF THE TIME the delimiters follows
        // the LIFO (Last-In, First-Out), so we check first the last opened delimiter and then the olders.
        for (int i = pointers.size() - 1; i >= 0; i--) {
            DelimiterPointer pointer = pointers.get(i);
            if (matches(pointer.closeDelimiter(), offset)) {
                return pointer;
            }
        }
        return null;
    }
}