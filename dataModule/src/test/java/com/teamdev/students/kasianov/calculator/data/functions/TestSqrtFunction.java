package com.teamdev.students.kasianov.calculator.data.functions;

import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.Assert.*;

public class TestSqrtFunction {
    private SqrtFunction sqrtFunction = new SqrtFunction(1,1);

    @Test
    public void testSqrtWithOneArgument() {
        assertEquals("Unexpected result with one argument (9)",
                new BigDecimal(3),
                sqrtFunction.calculate(new BigDecimal(9)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSqrtWithTwoArguments() {
        sqrtFunction.calculate(new BigDecimal(9), new BigDecimal(16));
        fail("Function with two arguments didn't throw an IllegalArgumentException");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSqrtWithNegativeArgument() {
        sqrtFunction.calculate(new BigDecimal(-9));
        fail("Function with negative argument value didn't throw an IllegalArgumentException");
    }

}
