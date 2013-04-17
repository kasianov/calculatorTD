package com.teamdev.students.kasianov.calculator.data.functions;

import java.math.BigDecimal;
import java.math.MathContext;

public class SqrtFunction extends AbstractFunction {
    public SqrtFunction(int maximumArgumentsCount, int minimumArgumentsCount) {
        super(maximumArgumentsCount, minimumArgumentsCount);
    }

    @Override
    public BigDecimal calculate(BigDecimal... arguments) {
        if (arguments.length != 1) {
            throw new IllegalArgumentException("Wrong number of arguments in sqrt function: " + arguments.length);
        }
        if (arguments[0].compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Negative numbers are not allowed in sqrt function.");
        }
        return new BigDecimal(Math.sqrt(arguments[0].doubleValue()), MathContext.DECIMAL32);
    }

}
