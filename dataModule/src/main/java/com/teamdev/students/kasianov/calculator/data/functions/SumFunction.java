package com.teamdev.students.kasianov.calculator.data.functions;

import java.math.BigDecimal;

public class SumFunction extends AbstractFunction {
    public SumFunction(int maximumArgumentsCount, int minimumArgumentsCount) {
        super(maximumArgumentsCount, minimumArgumentsCount);
    }

    @Override
    public BigDecimal calculate(BigDecimal... arguments) {
        if (arguments.length < 2) {
            throw new IllegalArgumentException("Wrong number of arguments in sum function. " + arguments.length);
        }
        BigDecimal sum = new BigDecimal(0);
        for (BigDecimal argument : arguments) {
            sum = sum.add(argument);
        }
        return sum;
    }
}
