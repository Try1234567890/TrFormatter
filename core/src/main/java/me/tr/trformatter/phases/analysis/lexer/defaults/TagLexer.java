package me.tr.trformatter.phases.analysis.lexer.defaults;

import me.tr.trformatter.phases.analysis.lexer.tokens.NameToken;
import me.tr.trformatter.phases.analysis.lexer.tokens.components.FunctionToken;
import me.tr.trformatter.phases.analysis.lexer.tokens.components.TagToken;
import me.tr.trformatter.phases.analysis.lexer.tokens.params.ParamToken;
import me.tr.trformatter.phases.analysis.scanner.chars.CharacterSet;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawTag;
import me.tr.trformatter.strings.Text;
import me.tr.utilities.validators.Preconditions;

import java.util.ArrayList;
import java.util.List;

public class TagLexer extends GenericLexer<IndexedRawTag> {
    public static final TagLexer INSTANCE = new TagLexer(CharacterSet.DEFAULT);

    protected TagLexer(CharacterSet characters) {
        super(characters);
    }

    public static TagLexer of(CharacterSet characters) {
        if (characters == null || characters.isDefault()) {
            return INSTANCE;
        }
        return new TagLexer(characters);
    }

    public static TagLexer of() {
        return INSTANCE;
    }

    @Override
    public TagToken tokenize(IndexedRawTag rawComponent) {
        Preconditions.parameterNotNull(rawComponent, "rawComponent");


        NameToken name = getName(new Text(rawComponent.component()));
        List<ParamToken> params = getParams(rawComponent);

        if (rawComponent.hasFunctions()) {
            List<FunctionToken> functions = FunctionsLexer.INSTANCE.tokenize(rawComponent.functions());
            return new TagToken(name, params, functions);
        }

        return new TagToken(name, params);
    }

    public List<TagToken> tokenize(List<IndexedRawTag> rawComponents) {
        List<TagToken> tokens = new ArrayList<>();

        for (IndexedRawTag rawComponent : rawComponents) {
            TagToken token = tokenize(rawComponent);

            if (token == null) continue;

            tokens.add(token);
        }

        return tokens;
    }
}
