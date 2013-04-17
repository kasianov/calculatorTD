package com.teamdev.students.kasianov.calculator.data.operators;

import java.math.BigDecimal;


public class PlusBinaryOperator extends AbstractBinaryOperator {
    public PlusBinaryOperator(Priority priority) {
        super(priority);
    }

    @Override
    public BigDecimal calculate(BigDecimal leftOperand, BigDecimal rightOperand) {
        return leftOperand.add(rightOperand);
    }
}
