package com.teamdev.studnets.kasianov.calculator.implementation.parsers;

import com.teamdev.studnets.kasianov.calculator.implementation.ExpressionReader;
import com.teamdev.studnets.kasianov.calculator.implementation.commands.EndOfExpressionCommand;
import com.teamdev.studnets.kasianov.calculator.implementation.commands.EvaluationCommand;

public class EndOfExpressionParser implements MathParser {
    @Override
    public EvaluationCommand parse(ExpressionReader reader) {
        reader.skipSpaces();
        if (reader.isEndOfExpression()) {
            reader.setPosition(reader.getPosition());
            return new EndOfExpressionCommand();
        }
        return null;
    }
}
