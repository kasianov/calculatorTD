package com.teamdev.students.kasianov.calculator.data.functions;

import java.math.BigDecimal;
import java.math.MathContext;

public class PiFunction extends AbstractFunction {
    public PiFunction(int maximumArgumentsCount, int minimumArgumentsCount) {
        super(maximumArgumentsCount, minimumArgumentsCount);
    }

    @Override
    public BigDecimal calculate(BigDecimal... arguments) {
        if (arguments.length != 0) {
            throw new IllegalArgumentException("Wrong number of arguments in pi() function. " + arguments.length);
        }
        return new BigDecimal(Math.PI, MathContext.DECIMAL32);
    }
}
