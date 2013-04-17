package com.teamdev.students.kasianov.calculator.data.operators;

import org.junit.Test;

import java.math.BigDecimal;

import static com.teamdev.students.kasianov.calculator.data.operators.AbstractBinaryOperator.Priority.LOW;
import static com.teamdev.students.kasianov.calculator.data.operators.AbstractBinaryOperator.Priority.MEDIUM;
import static junit.framework.Assert.assertEquals;

public class TestMultiplyBinaryOperator {
    private MultiplyBinaryOperator multiplyBinaryOperator = new MultiplyBinaryOperator(MEDIUM);

    @Test
    public void testMultiply() {
        assertEquals("Unexpected result (21*3)",
                new BigDecimal(63),
                multiplyBinaryOperator.calculate(
                        new BigDecimal(21),
                        new BigDecimal(3)));
    }

    @Test
    public void testMultiplyByZero() {
        assertEquals("Unexpected result (21*0)",
                new BigDecimal(0),
                multiplyBinaryOperator.calculate(
                        new BigDecimal(21),
                        new BigDecimal(0)));
    }

}
