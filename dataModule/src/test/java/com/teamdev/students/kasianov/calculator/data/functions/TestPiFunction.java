package com.teamdev.students.kasianov.calculator.data.functions;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static junit.framework.Assert.*;

public class TestPiFunction {
    private PiFunction piFunction = new PiFunction(0,0);

    @Test
    public void testPi() {
        assertEquals("Unexpected result",
                new BigDecimal(Math.PI, MathContext.DECIMAL32),
                piFunction.calculate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPiWithOneArgument() {
        piFunction.calculate(new BigDecimal(1));
        fail("Function with one argument didn't throw an exception");
    }

}
