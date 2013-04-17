package com.teamdev.students.kasianov.calculator.data.operators;

import java.math.BigDecimal;
import java.math.MathContext;

public class PowerBinaryOperator extends AbstractBinaryOperator {
    public PowerBinaryOperator(Priority priority) {
        super(priority);
    }

    @Override
    public BigDecimal calculate(BigDecimal leftOperand, BigDecimal rightOperand) {
        return new BigDecimal(Math.pow(leftOperand.doubleValue(),rightOperand.doubleValue()), MathContext.DECIMAL32);
    }
}
