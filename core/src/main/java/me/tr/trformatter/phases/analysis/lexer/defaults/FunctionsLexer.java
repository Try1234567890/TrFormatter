package me.tr.trformatter.phases.analysis.lexer.defaults;

import me.tr.trformatter.phases.analysis.lexer.tokens.NameToken;
import me.tr.trformatter.phases.analysis.lexer.tokens.components.FunctionToken;
import me.tr.trformatter.phases.analysis.lexer.tokens.params.ParamToken;
import me.tr.trformatter.phases.analysis.scanner.chars.CharacterSet;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawFunction;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawFunctions;
import me.tr.trformatter.strings.Text;
import me.tr.utilities.validators.Preconditions;

import java.util.ArrayList;
import java.util.List;

public class FunctionsLexer extends GenericLexer<IndexedRawFunction> {
    public static final FunctionsLexer INSTANCE = new FunctionsLexer(CharacterSet.DEFAULT);

    protected FunctionsLexer(CharacterSet characters) {
        super(characters);
    }

    public static FunctionsLexer of(CharacterSet characters) {
        if (characters == null || characters.isDefault()) {
            return INSTANCE;
        }
        return new FunctionsLexer(characters);
    }

    public static FunctionsLexer of() {
        return INSTANCE;
    }

    @Override
    public FunctionToken tokenize(IndexedRawFunction rawComponent) {
        Preconditions.parameterNotNull(rawComponent, "rawComponent");
        NameToken name = getName(new Text(rawComponent.component()));
        List<ParamToken> params = getParams(rawComponent);

        return new FunctionToken(name, params);
    }

    public List<FunctionToken> tokenize(IndexedRawFunctions rawComponents) {
        return tokenize(rawComponents.functions());
    }

    public List<FunctionToken> tokenize(List<IndexedRawFunction> rawComponents) {

        List<FunctionToken> tokens = new ArrayList<>();

        for (IndexedRawFunction rawComponent : rawComponents) {
            FunctionToken token = tokenize(rawComponent);

            if (token == null) continue;

            tokens.add(token);
        }

        return tokens;
    }
}
