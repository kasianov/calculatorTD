package com.teamdev.students.kasianov.calculator.data.operators;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;


public class DivideBinaryOperator extends AbstractBinaryOperator {
    public DivideBinaryOperator(Priority priority) {
        super(priority);
    }

    @Override
    public BigDecimal calculate(BigDecimal leftOperand, BigDecimal rightOperand) {
        if (BigDecimal.ZERO.compareTo(rightOperand) == 0) {
            throw new IllegalArgumentException("Division by zero.");
        }
        return leftOperand.divide(rightOperand,MathContext.DECIMAL32);
    }
}
