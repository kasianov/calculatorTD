package com.teamdev.students.kasianov.calculator.data.functions;

import java.math.BigDecimal;

public class MainFunction extends AbstractFunction {
    @Override
    public BigDecimal calculate(BigDecimal... arguments) {
        if (arguments.length < getMinimumArgumentsCount()
                || arguments.length > getMaximumArgumentsCount()) {
            throw new IllegalArgumentException("Wrong number of arguments in main function. " + arguments.length);
        }
        return arguments[0];
    }

    public MainFunction(int maximumArgumentsCount, int minimumArgumentsCount) {
        super(maximumArgumentsCount, minimumArgumentsCount);
    }
}
