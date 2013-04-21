package com.teamdev.studnets.kasianov.calculator.implementation;

import com.teamdev.students.kasianov.calculator.AbstractFiniteStateMachine;

import java.math.BigDecimal;

public class Calculator extends AbstractFiniteStateMachine<EvaluationState,
        EvaluationContext,
        EvaluationTransitionMatrix,
        EvaluationStateRecognizer,
        EvaluationException> {

    private final EvaluationTransitionMatrix matrix;
    private final EvaluationStateRecognizer recognizer;

    public Calculator(EvaluationTransitionMatrix matrix, EvaluationStateRecognizer recognizer) {
        this.matrix = matrix;
        this.recognizer = recognizer;
    }

    public static Calculator createCalculator() {
        return new Calculator(new EvaluationTransitionMatrix(), new EvaluationStateRecognizer());
    }

    public BigDecimal calculate(String mathExpression) throws EvaluationException {
        EvaluationContext context = EvaluationContext.createEvaluationContext(mathExpression);
        run(context);
        //todo
        return null;
        //return context.getResult();
    }

    @Override
    public void deadlock(EvaluationContext context) throws EvaluationException {
        //if there is no error message set, then state machine failed with unknown symbol
        //context.errorOccurred() - sets the appropriate error message
        if (context.getErrorMessage() == null || context.getErrorMessage().length() == 0) {
            context.errorOccurred();
        } else {
            //otherwise some error token was recognized and parsed and error message was set, so the current read position
            //has been moved forward by a parser, to point to the error position rollbackPosition() is invoked
            context.getExpressionReader().rollbackPosition();
        }
        throw new EvaluationException(context.getErrorMessage(), context.getExpressionReader().getPosition());
    }

    @Override
    public EvaluationTransitionMatrix getTransitionMatrix() {
        return matrix;
    }

    @Override
    public EvaluationStateRecognizer getStateRecognizer() {
        return recognizer;
    }
}
