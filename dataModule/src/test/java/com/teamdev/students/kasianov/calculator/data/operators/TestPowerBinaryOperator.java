package com.teamdev.students.kasianov.calculator.data.operators;

import org.junit.Test;

import java.math.BigDecimal;

import static com.teamdev.students.kasianov.calculator.data.operators.AbstractBinaryOperator.Priority.HIGH;
import static junit.framework.Assert.assertEquals;

public class TestPowerBinaryOperator {
    private PowerBinaryOperator powerBinaryOperator = new PowerBinaryOperator(HIGH);

    @Test
    public void testPower() {
        assertEquals("Unexpected result (21^3)",
                new BigDecimal(Math.pow(21, 3)),
                powerBinaryOperator.calculate(
                        new BigDecimal(21),
                        new BigDecimal(3)));
    }

    @Test
    public void testPowerByZero() {
        assertEquals("Unexpected result (21^0)",
                new BigDecimal(1),
                powerBinaryOperator.calculate(
                        new BigDecimal(21),
                        new BigDecimal(0)));
    }

}
