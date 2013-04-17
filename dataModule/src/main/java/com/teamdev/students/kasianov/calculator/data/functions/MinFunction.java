package com.teamdev.students.kasianov.calculator.data.functions;

import java.math.BigDecimal;

public class MinFunction extends AbstractFunction {
    public MinFunction(int maximumArgumentsCount, int minimumArgumentsCount) {
        super(maximumArgumentsCount, minimumArgumentsCount);
    }

    @Override
    public BigDecimal calculate(BigDecimal... arguments) {
        if (arguments.length < 2) {
            throw new IllegalArgumentException("Wrong number of arguments in min function. " + arguments.length);
        }
        BigDecimal min = arguments[0];
        for (BigDecimal argument : arguments) {
            if (min.compareTo(argument) > 0) {
                min = argument;
            }
        }
        return min;
    }
}
