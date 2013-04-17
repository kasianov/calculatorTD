package com.teamdev.students.kasianov.calculator.data.operators;

import java.math.BigDecimal;
import java.math.MathContext;

public class MultiplyBinaryOperator extends AbstractBinaryOperator {
    public MultiplyBinaryOperator(Priority priority) {
        super(priority);
    }

    @Override
    public BigDecimal calculate(BigDecimal leftOperand, BigDecimal rightOperand) {
        return leftOperand.multiply(rightOperand, MathContext.DECIMAL32);
    }
}
