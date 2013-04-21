package com.teamdev.studnets.kasianov.calculator.implementation;

import com.teamdev.students.kasianov.calculator.data.functions.Function;
import com.teamdev.students.kasianov.calculator.data.operators.BinaryOperator;

import java.math.BigDecimal;

/**
 * An object which provides operations to process parsing of a math expression
 *
 * @param <MathException> - an exception that can be thrown by any operation
 */
public interface Evaluator<MathException extends Exception> {
    void pushNumber(BigDecimal number) throws MathException;

    void pushFunction(Function function) throws MathException;

    void pushBinaryOperator(BinaryOperator binaryOperator) throws MathException;

    void pushLeftParenthesis() throws MathException;

    void pushRightParenthesis() throws MathException;

    void pushFunctionSeparator() throws MathException;

    void pushVariable(String variable) throws MathException;

    void pushExpressionSeparator() throws MathException;

    void pushEquationSign() throws MathException;

    void popOutStack() throws MathException;

    BigDecimal getResult() throws MathException;
}
