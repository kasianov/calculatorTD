package com.teamdev.studnets.kasianov.calculator.implementation.commands;

import com.teamdev.studnets.kasianov.calculator.implementation.EvaluationContext;

public class VariableCommand implements EvaluationCommand {
    private final String variable;

    public VariableCommand(String variable) {
        this.variable = variable;
    }

    @Override
    public void perform(EvaluationContext context) {
        if (context.isInErrorState()) {
            context.setErrorMessage("Unexpected variable");
            return;
        }
        context.pushVariable(variable);
    }
}
