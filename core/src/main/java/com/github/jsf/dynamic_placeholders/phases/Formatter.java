package com.github.jsf.dynamic_placeholders.phases;

import com.github.jsf.dynamic_placeholders.components.*;
import com.github.jsf.dynamic_placeholders.components.impls.actions.Action;
import com.github.jsf.dynamic_placeholders.components.impls.conditions.Condition;
import com.github.jsf.dynamic_placeholders.components.impls.functions.Function;
import com.github.jsf.dynamic_placeholders.phases.resolution.lexer.*;
import com.github.jsf.dynamic_placeholders.phases.resolution.lexer.tokens.Tokens;
import com.github.jsf.dynamic_placeholders.phases.resolution.parser.*;
import com.github.jsf.dynamic_placeholders.phases.resolution.scanner.DPScanner;
import com.github.jsf.dynamic_placeholders.phases.resolution.scanner.beans.DPDelimiterSet;
import com.github.jsf.dynamic_placeholders.phases.resolution.scanner.beans.IndexedDPComponent;
import com.github.jsf.scanners.beans.IndexedComponent;
import com.github.jsf.scanners.impls.DelimiterScanner;
import com.github.jsf.text.Text;
import com.github.utilities.validators.Preconditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Formatter {
    private final Text text;
    private final DPDelimiterSet delimiters;
    private List<IndexedDPComponent> components;
    private List<Tokens> tokens;
    private List<DynamicPlaceholder> placeholders;
    private List<String> results;

    private final DPScanner scanner;
    private final DelimiterScanner actions;
    private final DelimiterScanner conditions;
    private final DelimiterScanner functions;
    private Text formattedText;

    public Formatter(Text text, DPDelimiterSet delimiters) {
        this.text = Preconditions.parameterNotNull(text, "text");
        this.delimiters = Preconditions.parameterNotNull(delimiters, "delimiters");

        this.scanner = new DPScanner(text, delimiters);
        this.actions = new DelimiterScanner(text, delimiters.getActions());
        this.conditions = new DelimiterScanner(text, delimiters.getConditions());
        this.functions = new DelimiterScanner(text, delimiters.getFunctions());
    }

    public Formatter(Text text) {
        this(text, DPDelimiterSet.DEFAULT);
    }

    public Text text() {
        return text;
    }

    public DPDelimiterSet delimiters() {
        return delimiters;
    }

    /**
     * Retrieve a copy of the scanned components if present, otherwise an empty list.
     * <p>
     * The {@link #components} field is lazy initialized inside {@link #format()}.
     *
     * @return The list of the scanned components if present, otherwise an empty list.
     */
    public List<IndexedDPComponent> components() {
        return components != null ? new ArrayList<>(components) : new ArrayList<>();
    }

    /**
     * Retrieve a copy of the tokenized components if present, otherwise an empty list.
     * <p>
     * The {@link #tokens} field is lazy initialized inside {@link #format()}.
     *
     * @return The list of the tokenized components if present, otherwise an empty list.
     */
    public List<Tokens> tokens() {
        return tokens != null ? new ArrayList<>(tokens) : new ArrayList<>();
    }

    /**
     * Retrieve a copy of the parsed components if present, otherwise an empty list.
     * <p>
     * The {@link #tokens} field is lazy initialized inside {@link #format()}.
     *
     * @return The list of the parsed components if present, otherwise an empty list.
     */
    public List<DynamicPlaceholder> placeholders() {
        return placeholders != null ? new ArrayList<>(placeholders) : new ArrayList<>();
    }

    /**
     * Retrieve a results copy of the placeholder evaluations if present, otherwise an empty list.
     * <p>
     * The {@link #tokens} field is lazy initialized inside {@link #format()}.
     *
     * @return The results list of the placeholder evaluations if present, otherwise an empty list.
     */
    public List<String> results() {
        return results != null ? new ArrayList<>(results) : new ArrayList<>();
    }

    /**
     * Checks if the text matches exactly to a dynamic placeholder.
     *
     * @return {@code true} if the text matches a dynamic placeholder.
     */
    public boolean matchesDynamicPlaceholder() {
        Optional<IndexedDPComponent> placeholder = scanner.scanFirst();
        return placeholder
                .map(component -> component.start() == 0 && component.end() == text.length())
                .orElse(false);
    }

    /**
     * Checks if the text matches exactly to an action.
     *
     * @return {@code true} if the text matches an action.
     */
    public boolean matchesAction() {
        Optional<IndexedComponent> action = actions.scanFirst();
        return action
                .map(component -> component.start() == 0 && component.end() == text.length())
                .orElse(false);
    }

    /**
     * Checks if the text matches exactly to a condition.
     *
     * @return {@code true} if the text matches a condition.
     */
    public boolean matchesCondition() {
        Optional<IndexedComponent> condition = conditions.scanFirst();
        return condition
                .map(component -> component.start() == 0 && component.end() == text.length())
                .orElse(false);
    }

    /**
     * Checks if the text matches exactly to a function.
     *
     * @return {@code true} if the text matches a function.
     */
    public boolean matchesFunction() {
        Optional<IndexedComponent> function = functions.scanFirst();
        return function
                .map(component -> component.start() == 0 && component.end() == text.length())
                .orElse(false);
    }

    /**
     * Checks if the text contains any {@link DynamicPlaceholder}.
     *
     * @return {@code true} if contains placeholders, otherwise {@code false}.
     */
    public boolean containsDynamicPlaceholders() {
        return scanner.hasNext();
    }

    public Optional<Action> asAction() {
        if (!matchesAction()) {
            return Optional.empty();
        }
        return asComponent(actions, ic -> new DPActionLexer(ic, delimiters), tokens -> new DPActionParser(tokens, delimiters));
    }

    public Optional<Condition> asCondition() {
        if (!matchesCondition()) {
            return Optional.empty();
        }
        return asComponent(conditions, ic -> new DPConditionLexer(ic, delimiters), tokens -> new DPConditionParser(tokens, delimiters));
    }

    public Optional<Function> asFunction() {
        if (!matchesFunction()) {
            return Optional.empty();
        }
        return asComponent(functions, ic -> new DPFunctionLexer(ic, delimiters), tokens -> new DPFunctionParser(tokens, delimiters));
    }

    public Optional<DynamicPlaceholder> asDynamicPlaceholder() {
        if (!matchesDynamicPlaceholder()) {
            return Optional.empty();
        }
        Optional<IndexedDPComponent> dynPL = scanner.scanFirst();
        if (dynPL.isEmpty()) return Optional.empty();
        Tokens tokens = new DPLexer(dynPL.get(), delimiters).tokenize();
        return Optional.ofNullable(new DPParser(tokens, delimiters).parse());
    }

    private <T extends Component<?>> Optional<T> asComponent(DelimiterScanner scanner,
                                                             java.util.function.Function<IndexedComponent, DPComponentLexer> lexer,
                                                             java.util.function.Function<Tokens, DPComponentParser<T>> parser) {
        Optional<IndexedComponent> component = scanner.scanFirst();
        if (component.isEmpty()) return Optional.empty();
        Tokens tokens = lexer.apply(component.get()).tokenize();
        return Optional.ofNullable(parser.apply(tokens).parse());
    }

    /**
     * Format the {@link #text} by searching and replacing all {@link DynamicPlaceholder}s
     * found inside it.
     *
     * @return the new Text formatted.
     */
    public Text format() {
        if (formattedText != null) {
            return formattedText;
        }

        this.components = scanner.scanAll();

        if (components.isEmpty()) {
            return text;
        }

        this.tokens = tokenize(components);
        this.placeholders = parse(tokens);
        this.results = evaluate(placeholders);
        return (this.formattedText = new DPInterpolator(text, placeholders).interpolate());
    }


    /**
     * Tokenize the provided {@code components} with the {@link DPLexer}.
     *
     * @param components The components to tokenize.
     * @return The list of the every component tokens.
     */
    private List<Tokens> tokenize(List<IndexedDPComponent> components) {
        List<Tokens> result = new ArrayList<>();

        for (IndexedDPComponent component : components) {
            Tokens tokens = new DPLexer(component, delimiters).tokenize();
            result.add(tokens);
        }

        return result;
    }

    /**
     * Parse the given {@code tokensList} to effective {@link DynamicPlaceholder}s.
     *
     * @param tokensList the tokensList list of each component.
     * @return The list of dynamic placeholders parsed.
     */
    private List<DynamicPlaceholder> parse(List<Tokens> tokensList) {
        List<DynamicPlaceholder> placeholders = new ArrayList<>();

        for (Tokens tokens : tokensList) {
            DynamicPlaceholder placeholder = new DPParser(tokens, delimiters).parse();
            placeholders.add(placeholder);
        }

        return placeholders;
    }

    /**
     * Evaluate given {@link DynamicPlaceholder}s a returns their results.
     *
     * @param placeholders The placeholders to evaluate.
     * @return the list of the results.
     */
    private List<String> evaluate(List<DynamicPlaceholder> placeholders) {
        List<String> results = new ArrayList<>();

        for (DynamicPlaceholder placeholder : placeholders) {
            String result = placeholder.evaluate();
            results.add(result);
        }

        return results;
    }
}























