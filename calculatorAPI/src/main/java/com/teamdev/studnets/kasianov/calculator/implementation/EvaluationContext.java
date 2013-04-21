package com.teamdev.studnets.kasianov.calculator.implementation;

import com.teamdev.students.kasianov.calculator.FiniteStateMachineContext;
import com.teamdev.students.kasianov.calculator.data.functions.Function;
import com.teamdev.students.kasianov.calculator.data.operators.BinaryOperator;

import java.math.BigDecimal;

public class EvaluationContext implements FiniteStateMachineContext<EvaluationState, BigDecimal, EvaluationException> {

    final private ExpressionReader expressionReader;
    final private Evaluator<MathExpressionException> evaluator;
    private EvaluationState state;
    private String errorMessage;
    private boolean isInErrorState;

    private EvaluationContext(Evaluator<MathExpressionException> evaluator, ExpressionReader expressionReader) {
        this.evaluator = evaluator;
        this.expressionReader = expressionReader;
    }

    public static EvaluationContext createEvaluationContext(String mathExpression) {
        //return new EvaluationContext(new CalculatorEvaluator(), new ExpressionReader(mathExpression));
        return new EvaluationContext(new CalculatorEvaluatorWithContexts(), new ExpressionReader(mathExpression));
    }

    public ExpressionReader getExpressionReader() {
        return expressionReader;
    }

    @Override
    public void setInErrorState() {
        isInErrorState = true;
    }

    public boolean isInErrorState() {
        return isInErrorState;
    }

    /**
     * This method is invoked from StateMachineContext.deadlock method if no State was recognized
     * and no error had occurred before
     * so the symbol at the current position is unknown.
     */
    public void errorOccurred() {
        if (!getExpressionReader().isEndOfExpression()) {
            setError("Unexpected token ["
                    + getExpressionReader().getExpression().charAt(getExpressionReader().getPosition())
                    + "]");
        } else {
            setError("empty input string");
        }
    }

    @Override
    public BigDecimal getResult() throws EvaluationException {
        try {
            return evaluator.getResult();
        } catch (MathExpressionException ex) {
            throw new EvaluationException(ex.getMessage(), expressionReader.getPosition());
        }
    }

    @Override
    public EvaluationState getState() {
        return state;
    }

    @Override
    public void setState(EvaluationState evaluationState) {
        state = evaluationState;
    }

    public void pushNumber(BigDecimal number) {
        try {
            evaluator.pushNumber(number);
        } catch (MathExpressionException ex) {
            setError(ex.getMessage());
        }
    }

    public void pushFunction(Function function) {
        try {
            evaluator.pushFunction(function);
        } catch (MathExpressionException ex) {
            setError(ex.getMessage());
        }
    }

    public void pushBinaryOperator(BinaryOperator binaryOperator) {
        try {
            evaluator.pushBinaryOperator(binaryOperator);
        } catch (MathExpressionException ex) {
            setError(ex.getMessage());
        }
    }

    public void pushLeftParenthesis() {
        try {
            evaluator.pushLeftParenthesis();
        } catch (MathExpressionException ex) {
            setError(ex.getMessage());
        }
    }

    public void pushRightParenthesis() {
        try {
            evaluator.pushRightParenthesis();
        } catch (MathExpressionException ex) {
            setError(ex.getMessage());
        }
    }

    public void pushFunctionSeparator() {
        try {
            evaluator.pushFunctionSeparator();
        } catch (MathExpressionException ex) {
            setError(ex.getMessage());
        }
    }

    public void pushExpressionSeparator(){
        try {
            evaluator.pushExpressionSeparator();
        } catch (MathExpressionException ex) {
            setError(ex.getMessage());
        }
    }

    public void pushEquationSign(){
        try {
            evaluator.pushEquationSign();
        } catch (MathExpressionException ex) {
            setError(ex.getMessage());
        }
    }

    public void pushVariable(String variable){
        try {
            evaluator.pushVariable(variable);
        } catch (MathExpressionException ex) {
            setError(ex.getMessage());
        }
    }

    public void popOutStack() {
        try {
            evaluator.popOutStack();
        } catch (MathExpressionException ex) {
            setError(ex.getMessage());
        }
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Sets the Context in an error state with passed error message
     *
     * @param errorMessage - the reason of the error
     */
    private void setError(String errorMessage) {
        setInErrorState();
        setErrorMessage(errorMessage);
    }
}
