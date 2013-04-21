package com.teamdev.studnets.kasianov.calculator.implementation.parsers;

import com.teamdev.studnets.kasianov.calculator.implementation.ExpressionReader;
import com.teamdev.studnets.kasianov.calculator.implementation.commands.EvaluationCommand;
import com.teamdev.studnets.kasianov.calculator.implementation.commands.VariableCommand;

public class VariableParser implements MathParser {

    private final static String VARIABLE_REGEXP = "[a-zA-z_]+[a-zA-Z0-9_]*";

    private boolean match(String substring) {
        return substring.matches(VARIABLE_REGEXP);
    }

    @Override
    public EvaluationCommand parse(ExpressionReader reader) {
        reader.skipSpaces();
        String expression = reader.getExpression();
        int position = reader.getPosition();
        int count = 0;

        while (!reader.isEndOfExpression(count)) {
            if (!match(expression.substring(position, position + ++count))) {
                --count;
                break;
            }
        }

        if (count > 0) {
            reader.setPosition(position + count);
            return new VariableCommand(expression.substring(position, position + count));
        }

        return null;
    }
}
