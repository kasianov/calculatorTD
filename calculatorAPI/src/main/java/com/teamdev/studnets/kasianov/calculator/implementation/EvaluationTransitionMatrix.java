package com.teamdev.studnets.kasianov.calculator.implementation;

import com.teamdev.students.kasianov.calculator.TransitionMatrix;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

import static com.teamdev.studnets.kasianov.calculator.implementation.EvaluationState.*;

public class EvaluationTransitionMatrix implements TransitionMatrix<EvaluationState> {

    private final Map<EvaluationState, Set<EvaluationState>> evaluationStateSetMap =
            new EnumMap<EvaluationState, Set<EvaluationState>>(EvaluationState.class) {{
                put(START, EnumSet.of(
                        FUNCTION,
                        VARIABLE));

                put(VARIABLE,EnumSet.of(
                        BINARY_OPERATOR,
                        EQUATION_SIGN,
                        RIGHT_PARENTHESIS,
                        FUNCTION_SEPARATOR,
                        EXPRESSION_SEPARATOR));

                put(EXPRESSION_SEPARATOR,EnumSet.of(
                        FUNCTION,
                        VARIABLE,
                        FINISH));

                put(EQUATION_SIGN,EnumSet.of(
                        VARIABLE,
                        NUMBER,
                        FUNCTION,
                        LEFT_PARENTHESIS));

                put(NUMBER, EnumSet.of(
                        BINARY_OPERATOR,
                        RIGHT_PARENTHESIS,
                        EXPRESSION_SEPARATOR,
                        FUNCTION_SEPARATOR));

                put(POSITIVE_NUMBER, EnumSet.of(
                        BINARY_OPERATOR,
                        RIGHT_PARENTHESIS,
                        EXPRESSION_SEPARATOR,
                        FUNCTION_SEPARATOR));

                put(BINARY_OPERATOR, EnumSet.of(
                        POSITIVE_NUMBER,
                        VARIABLE,
                        LEFT_PARENTHESIS,
                        FUNCTION));

                put(FUNCTION, EnumSet.of(
                        LEFT_PARENTHESIS_AFTER_FUNCTION));

                put(FUNCTION_SEPARATOR, EnumSet.of(
                        LEFT_PARENTHESIS,
                        NUMBER,
                        VARIABLE,
                        FUNCTION));

                put(LEFT_PARENTHESIS, EnumSet.of(
                        NUMBER,
                        VARIABLE,
                        LEFT_PARENTHESIS,
                        FUNCTION));

                put(LEFT_PARENTHESIS_AFTER_FUNCTION, EnumSet.of(
                        NUMBER,
                        VARIABLE,
                        LEFT_PARENTHESIS,
                        RIGHT_PARENTHESIS,
                        FUNCTION));

                put(RIGHT_PARENTHESIS, EnumSet.of(
                        BINARY_OPERATOR,
                        EXPRESSION_SEPARATOR,
                        RIGHT_PARENTHESIS,
                        FUNCTION_SEPARATOR));
            }};

    @Override
    /**
     * Returns all possible states which the current state can be transmitted to
     * if there is no such state, then returns an empty Set
     */
    public Set<EvaluationState> getPossibleStates(EvaluationState evaluationState) {
        Set<EvaluationState> possibleStates = evaluationStateSetMap.get(evaluationState);
        return possibleStates != null ? possibleStates : EnumSet.noneOf(EvaluationState.class);
    }

    @Override
    /**
     * Checks out the 'evaluationState' if it is the finish state for state machine
     */
    public boolean isFinishState(EvaluationState evaluationState) {
        return evaluationState == FINISH;
    }

    @Override
    /**
     * Returns the start state for state machine
     */
    public EvaluationState getStartState() {
        return START;
    }

    @Override
    /**
     * Returns Set containing all EvaluationStates excluding the start state and
     * the ones which are contained in the 'states' Set
     */
    public Set<EvaluationState> getRestStates(Set<EvaluationState> states) {
        Set<EvaluationState> evaluationStates = EnumSet.allOf(EvaluationState.class);
        evaluationStates.removeAll(states);
        evaluationStates.remove(getStartState());
        return evaluationStates;
    }
}
