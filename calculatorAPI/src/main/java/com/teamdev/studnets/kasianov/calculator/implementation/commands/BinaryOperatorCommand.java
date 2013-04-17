package com.teamdev.studnets.kasianov.calculator.implementation.commands;

import com.teamdev.students.kasianov.calculator.data.operators.BinaryOperator;
import com.teamdev.studnets.kasianov.calculator.implementation.EvaluationContext;

public class BinaryOperatorCommand implements EvaluationCommand {

    private final BinaryOperator binaryOperator;

    public BinaryOperatorCommand(BinaryOperator binaryOperator) {
        this.binaryOperator = binaryOperator;
    }

    @Override
    public void perform(EvaluationContext context) {
        if (context.isInErrorState()) {
            context.setErrorMessage("Unexpected binary operator");
            return;
        }
        context.pushBinaryOperator(binaryOperator);
    }
}
