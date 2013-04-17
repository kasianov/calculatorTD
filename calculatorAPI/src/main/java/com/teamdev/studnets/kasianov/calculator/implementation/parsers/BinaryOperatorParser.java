package com.teamdev.studnets.kasianov.calculator.implementation.parsers;

import com.teamdev.students.kasianov.calculator.data.operators.BinaryOperator;
import com.teamdev.students.kasianov.calculator.data.operators.BinaryOperatorProvider;
import com.teamdev.studnets.kasianov.calculator.implementation.ExpressionReader;
import com.teamdev.studnets.kasianov.calculator.implementation.commands.BinaryOperatorCommand;
import com.teamdev.studnets.kasianov.calculator.implementation.commands.EvaluationCommand;

public class BinaryOperatorParser implements MathParser {
    private final BinaryOperatorProvider binaryOperatorProvider;

    public BinaryOperatorParser(BinaryOperatorProvider binaryOperatorProvider) {
        this.binaryOperatorProvider = binaryOperatorProvider;
    }

    @Override
    public EvaluationCommand parse(ExpressionReader reader) {
        reader.skipSpaces();

        if (reader.isEndOfExpression()) {
            return null;
        }
        final int position = reader.getPosition();
        final String expression = reader.getExpression();
        BinaryOperator binaryOperator;
        int count = 0;

        while (!reader.isEndOfExpression(count)) {
            if (!binaryOperatorProvider.findOperand(expression.substring(position, position + ++count))) {
                break;
            }
        }

        if(--count > 0){
            binaryOperator = binaryOperatorProvider.getBinaryOperator(expression.substring(position, position + count));
            if (binaryOperator != null) {
                reader.setPosition(position + count);
                return new BinaryOperatorCommand(binaryOperator);
            }
        }

        return null;
    }
}
