package com.teamdev.students.kasianov.calculator.data.operators;

import java.math.BigDecimal;

public class MinusBinaryOperator extends AbstractBinaryOperator {
    public MinusBinaryOperator(Priority priority) {
        super(priority);
    }

    @Override
    public BigDecimal calculate(BigDecimal leftOperand, BigDecimal rightOperand) {
        return leftOperand.subtract(rightOperand);
    }
}
