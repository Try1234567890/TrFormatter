package me.tr.trformatter.phases.analysis.scanner.defaults;

import me.tr.trformatter.phases.analysis.scanner.chars.CharacterSet;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawFunctions;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawParams;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawTag;
import me.tr.trformatter.strings.Text;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Specialized scanner for identifying and extracting structured tags
 * from a raw string within the trformatter analysis phase.
 * <p>
 * This class handles the separation between parameters and functions
 * based on the character configuration provided.
 */
public class TagScanner extends GenericScanner {
    /**
     * Singleton instance for default scanning operations.
     */
    public static final TagScanner INSTANCE =
            new TagScanner(CharacterSet.DEF_OPEN_TAG.getDelimiter(),
                    CharacterSet.DEF_CLOSE_TAG.getDelimiter(), CharacterSet.DEFAULT);

    /**
     * Initializes a new GenericScanner with specified delimiters and character rules.
     *
     * @param openDel    The string sequence identifying the start of a component.
     * @param closeDel   The string sequence identifying the end of a component.
     * @param characters The configuration for special characters. If null, default characters are used.
     * @throws NullPointerException if openDel or closeDel are null.
     */
    protected TagScanner(String openDel, String closeDel, CharacterSet characters) {
        super(openDel, closeDel, characters);
    }


    /**
     * Constructs a TagScanner with specific character delimiters.
     *
     * @param chars The character configuration to use. If null, default tags are used.
     */
    public static TagScanner of(CharacterSet chars) {
        if (chars == null
                || chars.isDefault()) {
            return INSTANCE;
        }
        return new TagScanner(
                chars.getOpenTag(),
                chars.getCloseTag(),
                chars
        );
    }

    public static TagScanner of() {
        return INSTANCE;
    }


    /**
     * Scans the text and returns a list specifically of {@link IndexedRawTag}.
     *
     * @param text  The source text to analyze.
     * @param from  The starting index.
     * @param end   The ending index (-1 for end of string).
     * @param depth The recursion depth.
     * @return A list of identified tags.
     */
    @Override
    public List<IndexedRawTag> scan(String text, int from, int end, int depth) {
        return super.scan(text, from, end, depth).stream()
                .filter(IndexedRawTag.class::isInstance)
                .map(IndexedRawTag.class::cast)
                .toList();
    }

    public List<IndexedRawTag> scan(String text, int start, int end) {
        return super.scan(text, start, end).stream()
                .filter(IndexedRawTag.class::isInstance)
                .map(IndexedRawTag.class::cast)
                .toList();
    }

    public List<IndexedRawTag> scan(String text, int start) {
        return super.scan(text, start).stream()
                .filter(IndexedRawTag.class::isInstance)
                .map(IndexedRawTag.class::cast)
                .toList();
    }

    public List<IndexedRawTag> scan(String text) {
        return super.scan(text).stream()
                .filter(IndexedRawTag.class::isInstance)
                .map(IndexedRawTag.class::cast)
                .toList();
    }

    public List<IndexedRawTag> scanMax(String text, int start, int depth) {
        return super.scanMax(text, start, depth).stream()
                .filter(IndexedRawTag.class::isInstance)
                .map(IndexedRawTag.class::cast)
                .toList();
    }

    public List<IndexedRawTag> scanMax(String text, int depth) {
        return super.scanMax(text, depth).stream()
                .filter(IndexedRawTag.class::isInstance)
                .map(IndexedRawTag.class::cast)
                .toList();
    }

    public Optional<IndexedRawTag> findFirst(String text) {
        return super.findFirst(text)
                .filter((component) -> component instanceof IndexedRawTag)
                .map(IndexedRawTag.class::cast);
    }

    public Optional<IndexedRawTag> findLast(String text) {
        return super.findFirst(text)
                .filter((component) -> component instanceof IndexedRawTag)
                .map(IndexedRawTag.class::cast);
    }

    public Stream<IndexedRawTag> stream(String text) {
        return super.stream(text)
                .filter((component) -> component instanceof IndexedRawTag)
                .map(IndexedRawTag.class::cast);
    }


    @Override
    public IndexedRawTag create(String text, int start, int end) {
        Text cText = new Text(text);
        String separator = characters().getSeparateFunction();
        int sepIndex = cText.indexOfIgnoringStrings(separator);

        if (sepIndex != -1) {
            String paramsPart = text.substring(0, sepIndex).trim();
            String funcPart = text.substring(sepIndex + 1).trim();

            IndexedRawParams params = ParamsScanner.INSTANCE.scanParamsOrNull(paramsPart);
            IndexedRawFunctions functions = FunctionScanner.INSTANCE.scanFunctionsOrNull(funcPart);

            return new IndexedRawTag(text, params, functions, start, end);
        }

        IndexedRawParams params = ParamsScanner.INSTANCE.scanParamsOrNull(text);
        return new IndexedRawTag(text, params, null, start, end);
    }
}