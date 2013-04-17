package com.teamdev.studnets.kasianov.calculator.implementation.commands;

import com.teamdev.studnets.kasianov.calculator.implementation.EvaluationContext;


public class FunctionSeparatorCommand implements EvaluationCommand {
    @Override
    public void perform(EvaluationContext context) {
        if (context.isInErrorState()) {
            context.setErrorMessage("Unexpected function separator");
            return;
        }
        context.pushFunctionSeparator();
    }
}
