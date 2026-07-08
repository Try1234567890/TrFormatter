package me.tr.trformatter.phases.analysis.scanner.defaults;

import me.tr.trformatter.phases.analysis.scanner.Scanner;
import me.tr.trformatter.phases.analysis.scanner.chars.CharacterSet;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawComponent;
import me.tr.trformatter.strings.Text;
import me.tr.utilities.validators.Preconditions;
import me.tr.utilities.validators.ValidationUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract base implementation of a {@link Scanner}.
 * This class serves as the core engine for all delimiter-based scanning operations.
 * It handles the complexity of finding balanced delimiters (nested structures) while
 * correctly ignoring any content inside string literals (single or double quotes) to
 * prevent false positive matches.
 */
public abstract class GenericScanner implements Scanner {
    private final String openDel;
    private final String closeDel;
    private final CharacterSet characters;

    /**
     * Initializes a new GenericScanner with specified delimiters and character rules.
     *
     * @param openDel    The string sequence identifying the start of a component.
     * @param closeDel   The string sequence identifying the end of a component.
     * @param characters The configuration for special characters. If null, default characters are used.
     * @throws NullPointerException if openDel or closeDel are null.
     */
    protected GenericScanner(String openDel, String closeDel, CharacterSet characters) {
        Preconditions.parameterNotNull(openDel, "open delimiter");
        Preconditions.parameterNotNull(closeDel, "close delimiter");
        this.openDel = openDel;
        this.closeDel = closeDel;
        this.characters = characters == null ? CharacterSet.DEFAULT : characters;
    }

    /**
     * Returns the character configuration used by this scanner.
     *
     * @return The {@link CharacterSet} instance containing separator and delimiter rules.
     */
    public CharacterSet characters() {
        return characters;
    }

    /**
     * Validates if the text has balanced delimiters.
     * <p>
     * <b>Note:</b> This is a simplified check for quick validation. It does not
     * account for delimiters inside string literals. For a precise check,
     * use the full scanning logic.
     * </p>
     *
     * @param text The text to validate.
     * @return true if the count of open delimiters matches the count of close delimiters.
     */
    public boolean isBalanced(String text) {
        if (text == null) return true;
        int balance = 0;
        int lastIdx = 0;
        while ((lastIdx = text.indexOf(openDel, lastIdx)) != -1) {
            balance++;
            lastIdx += openDel.length();
        }
        lastIdx = 0;
        while ((lastIdx = text.indexOf(closeDel, lastIdx)) != -1) {
            balance--;
            lastIdx += closeDel.length();
        }
        return balance == 0;
    }

    /**
     * Implementation of the {@link Scanner#scan} contract.
     * Iteratively searches for components within the given boundaries and recursion depth.
     *
     * @param text  The source text to analyze.
     * @param from  The starting index (clamped to 0).
     * @param end   The ending index (if negative, scans until the end of the string).
     * @param depth The maximum number of components to find (if negative, finds all).
     * @return A list of identified {@link IndexedRawComponent} implementations.
     */
    @Override
    public List<? extends IndexedRawComponent> scan(String text, int from, int end, int depth) {
        if (ValidationUtils.isNull(text)) return new ArrayList<>();

        List<IndexedRawComponent> components = new ArrayList<>();
        int currentPos = Math.max(0, from);
        int limit = (end < 0) ? text.length() : end;
        Text cText = new Text(text);

        while (currentPos < limit) {
            SearchResult result = this.findNextComponent(cText, currentPos);
            if (result.lastIndex() == -1) break;

            if (end >= 0 && result.lastIndex() > end) break;

            components.addAll(result.components());
            currentPos = result.lastIndex();

            if (depth > 0 && components.size() >= depth) break;
        }
        return components;
    }

    /**
     * Internal method to find the next valid component starting from a specific position.
     *
     * @param text  The source text wrapped in a {@link Text} for utility access.
     * @param start The index to start the search from.
     * @return A {@link SearchResult} containing found components and the next resume index.
     */
    private SearchResult findNextComponent(Text text, int start) {
        int o = text.indexOfIgnoringStrings(openDel, start);
        if (o == -1) return new SearchResult(new ArrayList<>(), -1);

        int contentStart = o + openDel.length();
        int c = findCloseIndex(text, contentStart);

        if (c != -1) {
            String rawContent = text.substring(contentStart, c);
            IndexedRawComponent comp = create(rawContent, o, c + closeDel.length());
            return new SearchResult(List.of(comp), c + closeDel.length());
        }

        return new SearchResult(new ArrayList<>(), contentStart);
    }

    /**
     * Finds the index of the closing delimiter that correctly matches the opening one,
     * respecting nesting levels and ignoring delimiters within quotes.
     *
     * @param text  The source text.
     * @param start The index right after the first detected opening delimiter.
     * @return The absolute index of the closing delimiter, or -1 if no matching closure is found.
     */
    private int findCloseIndex(Text text, int start) {
        int openAmt = 0;
        char quoteChar = 0;

        for (int i = start; i < text.length(); i++) {
            char ch = text.charAt(i);

            // Handle Strings: if we encounter a quote, we ignore everything until the quote is closed
            if (quoteChar == 0 && (ch == '\'' || ch == '\"')) {
                quoteChar = ch;
                continue;
            } else if (quoteChar == ch) {
                quoteChar = 0;
                continue;
            }

            if (quoteChar == 0) {
                if (text.matchesSequence(i, openDel.toCharArray())) {
                    openAmt++;
                } else if (text.matchesSequence(i, closeDel.toCharArray())) {
                    if (openAmt == 0) return i;
                    openAmt--;
                }
            }
        }
        return -1;
    }

    /**
     * Factory method to be implemented by subclasses.
     * Creates a specific component instance based on the raw text extracted between delimiters.
     *
     * @param text  The raw content found inside the delimiters.
     * @param start The absolute start index (at the opening delimiter).
     * @param end   The absolute end index (after the closing delimiter).
     * @return A concrete implementation of {@link IndexedRawComponent}.
     */
    protected abstract IndexedRawComponent create(String text, int start, int end);

    /**
     * A result container for a single scanning operation.
     *
     * @param components The list of components identified in this step.
     * @param lastIndex  The index where the scanner should resume the next search.
     */
    public record SearchResult(List<IndexedRawComponent> components, int lastIndex) {
    }
}