package com.teamdev.students.kasianov.calculator.implementation;

import com.teamdev.studnets.kasianov.calculator.implementation.Calculator;
import com.teamdev.studnets.kasianov.calculator.implementation.EvaluationException;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

public class TestCalculator {
    private Calculator calculator = Calculator.createCalculator();

    @Test
    public void testMinusPlus() throws EvaluationException {
        assertEquals("Unexpected result of '2+3-4+5+1-4+5'",
                new BigDecimal(8),
                calculator.calculate("2+3-4+5+1-4+5"));
    }

    @Test
    public void testMinusPlusWithParentheses() throws EvaluationException {
        assertEquals("Unexpected result of '5+(4-2)-3-(10-6)-(10-15)'",
                new BigDecimal(5),
                calculator.calculate("5+(4-2)-3-(10-6)-(10-15)"));
    }

    @Test
    public void testMultiplyDivide() throws EvaluationException {
        assertEquals("Unexpected result of '2*3/3/2*10*3/15'",
                new BigDecimal(2),
                calculator.calculate("2*3/3/2*10*3/15"));
    }

    @Test
    public void testMultiplyDivideWithParentheses() throws EvaluationException {
        assertEquals("Unexpected result of '2*((5*(4*(3*4/2)/8))/3)'",
                new BigDecimal(10),
                calculator.calculate("2*((5*(4*(3*4/2)/8))/3)"));
    }

    @Test
    public void testPower() throws EvaluationException {
        assertEquals("Unexpected result of '2*2^(81/3^3)'",
                new BigDecimal(16),
                calculator.calculate("2*2^(81/3^3)"));
    }

    @Test
    public void testSqrtMinMaxSum() throws EvaluationException {
        assertEquals("Unexpected result of 'sqrt((sum(min(6,4,3,1),min(6,3,2,7))+min(max(sqrt(16),min(1,2,3)),3)+12)/2)'",
                new BigDecimal(3),
                calculator.calculate("sqrt((sum(min(6,4,3,1),min(6,3,2,7))+min(max(sqrt(16),min(1,2,3)),3)+12)/2)"));
    }

    @Test
    public void testOperationPrecedence() throws EvaluationException {
        assertEquals("Unexpected result of '2+3*2^3/4-5'",
                new BigDecimal(3),
                calculator.calculate("2+3*2^3/4-5"));
    }

    @Test
    public void testOperationPrecedenceWithParentheses() throws EvaluationException {
        assertEquals("Unexpected result of '((4-2)+3*2^3/4-5-(5-10))/2'",
                new BigDecimal(4),
                calculator.calculate("((4-2)+3*2^3/4-5-(5-10))/2"));
    }

    @Test(expected = EvaluationException.class)
    public void testMismatchedParentheses() throws EvaluationException {
        calculator.calculate("10-(5-2");
        fail("Mismatched parentheses mistake was not found '10-(5-2'");
    }

    @Test(expected = EvaluationException.class)
    public void testMismatchedFunctionParentheses() throws EvaluationException {
        calculator.calculate("min(sqrt(9),2");
        fail("Mismatched parentheses mistake was not found 'min(sqrt(9),2'");
    }

}
