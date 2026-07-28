package com.github.jsf.dynamic_placeholders.phases.resolution.parser;

import com.github.jsf.dynamic_placeholders.components.Component;
import com.github.jsf.dynamic_placeholders.components.ComponentsInfo;
import com.github.jsf.dynamic_placeholders.components.Parameter;
import com.github.jsf.dynamic_placeholders.phases.resolution.lexer.tokens.Token;
import com.github.jsf.dynamic_placeholders.phases.resolution.lexer.tokens.TokenType;
import com.github.jsf.dynamic_placeholders.phases.resolution.lexer.tokens.Tokens;
import com.github.jsf.dynamic_placeholders.phases.resolution.parser.types.ParameterType;
import com.github.jsf.dynamic_placeholders.phases.resolution.scanner.beans.DPDelimiterSet;
import com.github.jsf.dynamic_placeholders.registries.ComponentsRegistry;
import com.github.jsf.scanners.IllegalComponentException;
import com.github.jsf.text.Text;
import com.github.utilities.validators.Preconditions;
import com.github.utilities.validators.ValidationUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class DPComponentParser<C extends Component<?>> extends Parser<C> {
    private final TokenType open;
    private final TokenType close;
    private final ComponentsRegistry<C> registry;

    protected DPComponentParser(Tokens tokens,
                                DPDelimiterSet delimiters,
                                TokenType open,
                                TokenType close,
                                ComponentsRegistry<C> registry) {
        super(tokens, delimiters);
        this.open = Preconditions.parameterNotNull(open, "open");
        this.close = Preconditions.parameterNotNull(close, "close");
        this.registry = Preconditions.parameterNotNull(registry, "registry");
    }

    @Override
    public C parse() {
        nextTokenAs(open, otherToken -> new UnexpectedTokenException("Unexpected token found: " + otherToken + " while parsing a component. Expected token is the open delimiter: " + open));

        String identifier = nextTokenAs(TokenType.IDENTIFIER,
                otherToken -> new UnexpectedTokenException("Unexpected token found while parsing a component. The found token is: " + otherToken + ". The expected token is: " + TokenType.IDENTIFIER)
        ).value().orElseThrow(() -> new UnexpectedTokenException("The identifier of the component is null or empty."));

        List<Parameter<?>> parameters = parseParameters(identifier);

        nextTokenAs(close, otherToken -> new UnexpectedTokenException("Unexpected token while parsing component: \"" + identifier + "\". The found token is: " + otherToken + ". Expected token is the close delimiter: " + close));

        return registry.getComponent(identifier)
                .map(component -> component.apply(new ComponentsInfo(parameters, tokens().range())))
                .orElseThrow(() -> new IllegalComponentException("The component with identifier \"" + identifier + "\" is not found inside the components registry (" + registry.getClass().getName() + ")!"));
    }

    public List<Parameter<?>> parseParameters(String componentID) {
        Token token = nextToken();
        List<Parameter<?>> parameters = new ArrayList<>();

        if (token == null || !token.type().equals(TokenType.OPEN_PARAMS)) return parameters;

        AtomicInteger currParamPos = new AtomicInteger(); // Used for logging purposes when the identifier token is not resolved yet.
        Token idToken;

        while ((idToken = nextToken()) != null && !idToken.type().equals(TokenType.CLOSE_PARAMS)) {
            currParamPos.incrementAndGet();
            if (idToken.type().equals(TokenType.SPLIT_PARAMS)) idToken = nextToken();
            if (!idToken.type().equals(TokenType.IDENTIFIER)) {
                throw new UnexpectedTokenException("Unexpected token while parsing parameters of component \"" +
                        componentID + "\": The " + currParamPos + " parameter identifier is expected, found: " + idToken);
            }

            String identifier = paramName(idToken, currParamPos.get());

            nextTokenAs(TokenType.ASSIGN_PARAM, otherToken -> new UnexpectedTokenException("Unexpected token is found after \"" + identifier + "\" parameter. The found token is: " + otherToken + ". The expected token is: " + TokenType.ASSIGN_PARAM));
            Token valueToken = nextTokenAs(TokenType.PARAM_VALUE, otherToken -> new UnexpectedTokenException("Unexpected token is found after \"" + identifier + "\" parameter. The found token is: " + otherToken + ". The expected token is: " + TokenType.PARAM_VALUE));

            ParameterType.Type value = paramValue(valueToken, identifier);

            //noinspection unchecked
            parameters.add(new Parameter<>(identifier, (ParameterType<Object>) value.type(), value.object()));
        }

        return parameters;
    }

    private String paramName(Token identifier, int currParamPos) {
        return identifier.value()
                .filter(ValidationUtils::isNotBlank)
                .orElseThrow(() -> new UnexpectedTokenException("The identifier of the " + currParamPos + " parameter is null or empty."));
    }

    private ParameterType.Type paramValue(Token valueToken, String identifier) {
        return valueToken.value()
                .filter(ValidationUtils::isNotBlank)
                .map(par -> ParameterType.typize(Text.of(par), delimiters()))
                .orElseThrow(() -> new UnexpectedTokenException("The value of the \"" + identifier + "\" parameter is null or empty."));
    }
}






















