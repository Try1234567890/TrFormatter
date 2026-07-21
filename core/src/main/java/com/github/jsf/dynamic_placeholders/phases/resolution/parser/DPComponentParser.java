package com.github.jsf.dynamic_placeholders.phases.resolution.parser;

import com.github.jsf.dynamic_placeholders.components.Component;
import com.github.jsf.dynamic_placeholders.components.Parameter;
import com.github.jsf.dynamic_placeholders.phases.resolution.lexer.tokens.Token;
import com.github.jsf.dynamic_placeholders.phases.resolution.lexer.tokens.TokenType;
import com.github.jsf.dynamic_placeholders.phases.resolution.parser.types.ParameterType;
import com.github.jsf.dynamic_placeholders.phases.resolution.scanner.beans.DPDelimiterSet;
import com.github.jsf.dynamic_placeholders.registries.ComponentsRegistry;
import com.github.jsf.scanners.IllegalComponentException;
import com.github.jsf.text.Text;
import com.github.utilities.validators.ValidationUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class DPComponentParser<C extends Component<?>> extends Parser<C> {
    private final TokenType open;
    private final TokenType close;
    private final ComponentsRegistry<C> registry;

    protected DPComponentParser(List<Token> tokens,
                                DPDelimiterSet delimiters,
                                TokenType open,
                                TokenType close,
                                ComponentsRegistry<C> registry) {
        super(tokens, delimiters);
        this.open = open;
        this.close = close;
        this.registry = registry;
    }

    @Override
    public C parse() {
        nextTokenAs(open).orElseThrow(() -> new UnexpectedTokenException("Unexpected token while parsing a component. Expected an open delimiter: " + open));
        String identifier = nextTokenAs(TokenType.IDENTIFIER)
                .map(token -> token.value().orElseThrow(() -> new UnexpectedTokenException("Unexpected token while parsing a component. An identifier is expected!")))
                .filter(ValidationUtils::isNotBlank)
                .orElseThrow(() -> new UnexpectedTokenException("The identifier of a component is null or empty."));
        List<Parameter<?>> parameters = parseParameters(identifier);
        nextTokenAs(close).orElseThrow(() -> new UnexpectedTokenException("Unexpected token while parsing component: \"" + identifier + "\". Expected a close delimiter: " + close));
        return registry.getComponent(identifier)
                .map(component -> component.apply(parameters))
                .orElseThrow(() -> new IllegalComponentException("The component with identifier \"" + identifier + "\" is not registered!"));
    }

    public List<Parameter<?>> parseParameters(String rawID) {
        Token token = nextToken();
        List<Parameter<?>> parameters = new ArrayList<>();
        if (token == null || !token.type().equals(TokenType.OPEN_PARAMS)) return parameters;

        AtomicInteger currParamPos = new AtomicInteger(); // Used for logging purposes when the identifier token is not resolved.
        Token idToken;
        while ((idToken = nextToken()) != null && !idToken.type().equals(TokenType.CLOSE_PARAMS)) {
            currParamPos.incrementAndGet();
            if (idToken.type().equals(TokenType.SPLIT_PARAMS)) idToken = nextToken();

            if (!idToken.type().equals(TokenType.IDENTIFIER)) {
                throw new UnexpectedTokenException("Unexpected token while parsing parameters of component \"" +
                        rawID + "\": The " + currParamPos + " parameter identifier is expected, found: " + idToken);
            }

            String identifier = id(idToken, currParamPos.get());

            nextTokenAs(TokenType.ASSIGN_PARAM).orElseThrow(() -> new UnexpectedTokenException("Unexpected token is found after \"" +
                    identifier + "\" parameter. The expected token is " + TokenType.ASSIGN_PARAM));

            Token valueToken = nextTokenAs(TokenType.PARAM_VALUE)
                    .orElseThrow(() -> new UnexpectedTokenException("Unexpected token is found after \"" + identifier + "\" parameter assigner. The expected token is " + TokenType.PARAM_VALUE));

            ParameterType.Type value = value(valueToken, identifier);

            //noinspection unchecked
            parameters.add(new Parameter<>(identifier, (ParameterType<Object>) value.type(), value.object()));
        }

        return parameters;
    }

    private String id(Token identifier, int currParamPos) {
        return identifier.value()
                .filter(ValidationUtils::isNotBlank)
                .orElseThrow(() -> new UnexpectedTokenException("The identifier of the " + currParamPos + " parameter is null or empty."));
    }

    private ParameterType.Type value(Token valueToken, String identifier) {
        return valueToken.value()
                .filter(ValidationUtils::isNotBlank)
                .map(par -> ParameterType.typize(Text.of(par), delimiters()))
                .orElseThrow(() -> new UnexpectedTokenException("The value of the \"" + identifier + "\" parameter is null or empty."));
    }
}






















