package com.teamdev.studnets.kasianov.calculator.implementation.parsers;

import com.teamdev.studnets.kasianov.calculator.implementation.ExpressionReader;
import com.teamdev.studnets.kasianov.calculator.implementation.commands.EquationSignCommand;
import com.teamdev.studnets.kasianov.calculator.implementation.commands.EvaluationCommand;

public class EquationSignParser implements MathParser {
    @Override
    public EvaluationCommand parse(ExpressionReader reader) {
        reader.skipSpaces();

        if (reader.isEndOfExpression()
                || reader.getExpression().charAt(reader.getPosition()) != '=') {
            return null;
        }
        reader.setPosition(reader.getPosition() + 1);
        return new EquationSignCommand();
    }
}
