package com.github.jsf.dynamic_placeholders.phases.resolution.lexer;

import com.github.jsf.dynamic_placeholders.phases.resolution.lexer.tokens.Token;
import com.github.jsf.dynamic_placeholders.phases.resolution.lexer.tokens.TokenType;
import com.github.jsf.dynamic_placeholders.phases.resolution.scanner.beans.DPDelimiterSet;
import com.github.jsf.scanners.IllegalComponentException;
import com.github.jsf.scanners.components.IndexedComponent;
import com.github.jsf.scanners.delimiters.Delimiter;
import com.github.jsf.scanners.delimiters.IdentifierDelimiter;
import com.github.jsf.scanners.delimiters.StringDelimiter;
import com.github.jsf.text.Text;
import com.github.utilities.validators.Preconditions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DPComponentLexer extends Lexer {
    private final Delimiter delimiter;
    private final TokenType open;
    private final TokenType close;
    private final Delimiter parametersDelimiter;
    private final StringDelimiter splitter;
    private final StringDelimiter assigner;

    public DPComponentLexer(IndexedComponent component,
                            Delimiter delimiter,
                            TokenType open,
                            TokenType close,
                            DPDelimiterSet set) {
        super(component, delimiter, set);
        this.delimiter = Preconditions.parameterNotNull(delimiter, "delimiter");
        this.parametersDelimiter = set.getParams();
        this.splitter = set.getParamsSplitter();
        this.assigner = set.getParamsAssigner();
        this.open = Preconditions.parameterNotNull(open, "open");
        this.close = Preconditions.parameterNotNull(close, "close");
    }

    public Delimiter delimiter() {
        return delimiter;
    }

    public TokenType open() {
        return open;
    }

    public TokenType close() {
        return close;
    }

    public Delimiter parametersDelimiter() {
        return parametersDelimiter;
    }

    public StringDelimiter splitter() {
        return splitter;
    }

    public StringDelimiter assigner() {
        return assigner;
    }

    public List<Token> tokenize() {
        consumeExpected(delimiter.open(), open);
        String identifier = readUntilAny(parametersDelimiter.open(), delimiter.close());
        if (identifier.isEmpty()) {
            throw new IllegalComponentException("Syntax Error: The component at index " + cursor() + " " +
                    " doesn't have an identifier. Found: '" + text().truncate(Math.max(0, cursor() - 5), Math.min(cursor() + 5, text().length()), "...").quoteWithSingle());
        }
        tokens().add(new Token(TokenType.IDENTIFIER, identifier));

        Map<String, String> parameters = new HashMap<>();
        if (match(this.parametersDelimiter.open())) {
            consumeExpected(parametersDelimiter.open(), TokenType.OPEN_PARAMS);
            parseParameters(parameters);
            consumeExpected(parametersDelimiter.close(), TokenType.CLOSE_PARAMS);
        }

        consumeExpected(delimiter.close(), close);

        return tokens();
    }

    private void parseParameters(Map<String, String> parameters) {
        String assigner = this.assigner.value();
        String splitter = this.splitter.value();

        while (!match(parametersDelimiter.close())
                && cursor() < text().length()) {

            String paramName = readUntil(assigner).trim();
            tokens().add(new Token(TokenType.IDENTIFIER, paramName));

            consumeExpected(assigner, TokenType.ASSIGN_PARAM);

            String paramValue = readParameterValue();
            tokens().add(new Token(TokenType.PARAM_VALUE, paramValue));
            parameters.put(paramName, paramValue);

            if (match(splitter)) {
                consumeExpected(splitter, TokenType.SPLIT_PARAMS);
            } else if (!match(parametersDelimiter.close())) {
                throw new IllegalStateException("Syntax error: No delimiters found for parameters closing at index " + cursor() + ". Found: " +
                        text().truncate(cursor(), Math.min(cursor() + 5, text().length()), "..."));
            }
        }
    }

    private String readParameterValue() {
        int index1 = text().indexOfFromNonBetweenStrings(splitter.value(), cursor());
        int index2 = text().indexOfFromNonBetweenStrings(parametersDelimiter.close(), cursor());
        if (index1 == -1) index1 = Integer.MAX_VALUE;
        if (index2 == -1) index2 = Integer.MAX_VALUE;

        int minIndex = Math.min(index1, index2);

        if (minIndex == Integer.MAX_VALUE) {
            throw new IllegalComponentException("Syntax error: No delimiters found to close the parameter assignment are found before index: " + cursor() +
                    ". Found: " + text().truncate(cursor(), Math.min(cursor() + 5, text().length()), "...").quoteWithSingle());
        }

        Text result = text().subtext(cursor(), minIndex);
        cursor(minIndex);
        return result.toString();
    }
}





















