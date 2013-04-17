package com.teamdev.students.kasianov.calculator.data.functions;

import java.math.BigDecimal;

public class MaxFunction extends AbstractFunction {
    public MaxFunction(int maximumArgumentsCount, int minimumArgumentsCount) {
        super(maximumArgumentsCount, minimumArgumentsCount);
    }

    @Override
    public BigDecimal calculate(BigDecimal... arguments) {
        if (arguments.length < 2) {
            throw new IllegalArgumentException("Wrong number of arguments in max function. " + arguments.length);
        }
        BigDecimal max = arguments[0];
        for (BigDecimal argument : arguments) {
            if (max.compareTo(argument) < 0) {
                max = argument;
            }
        }
        return max;
    }
}
