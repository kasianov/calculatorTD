package com.teamdev.studnets.kasianov.calculator.implementation.parsers;

import com.teamdev.studnets.kasianov.calculator.implementation.ExpressionReader;
import com.teamdev.studnets.kasianov.calculator.implementation.commands.EvaluationCommand;

public interface MathParser {
    EvaluationCommand parse(ExpressionReader expressionReader);
}
