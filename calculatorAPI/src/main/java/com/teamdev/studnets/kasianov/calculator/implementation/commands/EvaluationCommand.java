package com.teamdev.studnets.kasianov.calculator.implementation.commands;

import com.teamdev.studnets.kasianov.calculator.implementation.EvaluationContext;

public interface EvaluationCommand {
    void perform(EvaluationContext context);
}
