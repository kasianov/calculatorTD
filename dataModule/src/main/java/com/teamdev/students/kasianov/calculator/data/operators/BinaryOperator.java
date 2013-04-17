package com.teamdev.students.kasianov.calculator.data.operators;

import java.math.BigDecimal;

public interface BinaryOperator extends Comparable<BinaryOperator> {

    BigDecimal calculate(BigDecimal leftOperand, BigDecimal rightOperand);
}
