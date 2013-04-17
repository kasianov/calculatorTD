package com.teamdev.students.kasianov.calculator.data.operators;

import org.junit.Test;

import java.math.BigDecimal;

import static com.teamdev.students.kasianov.calculator.data.operators.AbstractBinaryOperator.Priority.MEDIUM;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

public class TestDivideBinaryOperator {
    private DivideBinaryOperator divideBinaryOperator = new DivideBinaryOperator(MEDIUM);

    @Test
    public void testDivide() {
        assertEquals("Unexpected result (21/3)",
                new BigDecimal(7),
                divideBinaryOperator.calculate(
                        new BigDecimal(21),
                        new BigDecimal(3)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDivideWithRightOperandZero(){
        divideBinaryOperator.calculate(BigDecimal.TEN,BigDecimal.ZERO);
        fail("Divide operator with right operand zero didn't throw an IllegalArgumentException");
    }
}
