package com.teamdev.students.kasianov.calculator.data.functions;

import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.Assert.*;

public class TestSumFunction {

    private SumFunction sumFunction = new SumFunction(Integer.MAX_VALUE,2);

    @Test
    public void testSumWithFourArguments() {
        BigDecimal result = sumFunction.calculate(
                new BigDecimal(2.5),
                new BigDecimal(4.5),
                new BigDecimal(3),
                new BigDecimal(10));
        assertEquals("Unexpected result with four arguments (2.5,4.5,3,10.0)", new BigDecimal("20.0"), result);
    }

    @Test
    public void testSumWithTwoArguments() {
        BigDecimal result = sumFunction.calculate(
                new BigDecimal(2.5),
                new BigDecimal(4.5));
        assertEquals("Unexpected result with two arguments (2.5,4.5)", new BigDecimal("7.0"), result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSumWithOneArgument() {
        sumFunction.calculate(new BigDecimal(10));
        fail("Function with one argument didn't throw an exception");
    }


}
