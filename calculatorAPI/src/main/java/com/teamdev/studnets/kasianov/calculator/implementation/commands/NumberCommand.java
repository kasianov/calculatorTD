package com.teamdev.studnets.kasianov.calculator.implementation.commands;

import com.teamdev.studnets.kasianov.calculator.implementation.EvaluationContext;

import java.math.BigDecimal;

public class NumberCommand implements EvaluationCommand {
    private final BigDecimal number;

    public NumberCommand(BigDecimal number) {
        this.number = number;
    }

    @Override
    public void perform(EvaluationContext context) {
        if (context.isInErrorState()) {
            context.setErrorMessage("Unexpected number");
            return;
        }
        context.pushNumber(number);
    }
}
