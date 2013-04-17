package com.teamdev.studnets.kasianov.calculator.implementation.commands;

import com.teamdev.students.kasianov.calculator.data.functions.Function;
import com.teamdev.studnets.kasianov.calculator.implementation.EvaluationContext;

public class FunctionCommand implements EvaluationCommand {
    private final Function function;

    public FunctionCommand(Function function) {
        this.function = function;
    }

    @Override
    public void perform(EvaluationContext context) {
        if (context.isInErrorState()) {
            context.setErrorMessage("Unexpected function");
            return;
        }
        context.pushFunction(function);
    }
}
