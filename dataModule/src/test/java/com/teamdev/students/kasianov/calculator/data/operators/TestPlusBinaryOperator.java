package com.teamdev.students.kasianov.calculator.data.operators;

import org.junit.Test;

import java.math.BigDecimal;

import static com.teamdev.students.kasianov.calculator.data.operators.AbstractBinaryOperator.Priority.LOW;
import static junit.framework.Assert.assertEquals;

public class TestPlusBinaryOperator {
    private PlusBinaryOperator plusBinaryOperator = new PlusBinaryOperator(LOW);

    @Test
    public void testPlus() {
        assertEquals("Unexpected result (21+3)",
                new BigDecimal(24),
                plusBinaryOperator.calculate(
                        new BigDecimal(21),
                        new BigDecimal(3)));
    }

}
