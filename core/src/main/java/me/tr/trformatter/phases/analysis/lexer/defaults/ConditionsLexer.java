package me.tr.trformatter.phases.analysis.lexer.defaults;

import me.tr.trformatter.phases.analysis.exceptions.ComponentNotFound;
import me.tr.trformatter.phases.analysis.lexer.tokens.NameToken;
import me.tr.trformatter.phases.analysis.lexer.tokens.OperatorToken;
import me.tr.trformatter.phases.analysis.lexer.tokens.components.ConditionToken;
import me.tr.trformatter.phases.analysis.lexer.tokens.params.ParamToken;
import me.tr.trformatter.phases.analysis.scanner.chars.CharacterSet;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawCondition;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawConditions;
import me.tr.trformatter.strings.Text;

import java.util.List;

public class ConditionsLexer extends GenericLexer<IndexedRawConditions> {
    public static final ConditionsLexer INSTANCE = new ConditionsLexer(CharacterSet.DEFAULT);

    protected ConditionsLexer(CharacterSet characters) {
        super(characters);
    }

    public static ConditionsLexer of(CharacterSet characters) {
        if (characters == null || characters.isDefault()) {
            return INSTANCE;
        }
        return new ConditionsLexer(characters);
    }

    public static ConditionsLexer of() {
        return INSTANCE;
    }

    @Override
    public ConditionToken tokenize(IndexedRawConditions rawConditions) {
        List<IndexedRawCondition> conditions = rawConditions.conditions();

        Text firstComponent = new Text(rawConditions.component());
        if (conditions.isEmpty()) return null;

        IndexedRawCondition firstRaw = conditions.getFirst();
        ConditionToken head = createToken(new Text(firstRaw.component()), firstRaw);
        ConditionToken currentLeft = head;

        for (int i = 1; i < conditions.size(); i++) {
            IndexedRawCondition nextRaw = conditions.get(i);

            Text nextComponent = new Text(nextRaw.component());
            OperatorToken.Operator operator = getOperator(firstComponent, conditions.get(i - 1).end(), nextRaw.start());
            ConditionToken nextToken = createToken(nextComponent, nextRaw);

            if (operator != null) {
                currentLeft.associate(operator, nextToken);
            } else {
                new ComponentNotFound("Cannot find the condition operator in between " + currentLeft + " and " + nextToken).printStackTrace(System.err);
            }

            currentLeft = nextToken;
        }

        return head;
    }

    private ConditionToken createToken(Text component, IndexedRawCondition raw) {
        NameToken name = getName(component);
        List<ParamToken> params = getParams(raw);
        return new ConditionToken(name, params);
    }

    private OperatorToken.Operator getOperator(Text component, int start, int end) {
        int and = component.indexOfIgnoringStrings(characters().getAndCondition(), start, end);
        int or = component.indexOfIgnoringStrings(characters().getOrCondition(), start, end);

        return and != -1 ? OperatorToken.Operator.AND : (or != -1 ? OperatorToken.Operator.OR : null);
    }
}
