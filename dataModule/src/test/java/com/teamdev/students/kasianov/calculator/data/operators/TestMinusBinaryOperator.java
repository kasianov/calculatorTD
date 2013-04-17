package com.teamdev.students.kasianov.calculator.data.operators;

import org.junit.Test;

import java.math.BigDecimal;

import static com.teamdev.students.kasianov.calculator.data.operators.AbstractBinaryOperator.Priority.LOW;
import static junit.framework.Assert.assertEquals;

public class TestMinusBinaryOperator {
    private MinusBinaryOperator minusBinaryOperator = new MinusBinaryOperator(LOW);

    @Test
    public void testMinus() {
        assertEquals("Unexpected result (21-3)",
                new BigDecimal(18),
                minusBinaryOperator.calculate(
                        new BigDecimal(21),
                        new BigDecimal(3)));
    }

}
