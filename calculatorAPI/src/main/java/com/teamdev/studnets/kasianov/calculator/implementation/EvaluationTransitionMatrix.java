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
                        NUMBER,
                        LEFT_PARENTHESIS,
                        FUNCTION));

                put(NUMBER, EnumSet.of(
                        BINARY_OPERATOR,
                        FINISH,
                        RIGHT_PARENTHESIS,
                        FUNCTION_SEPARATOR));

                put(POSITIVE_NUMBER, EnumSet.of(
                        BINARY_OPERATOR,
                        FINISH,
                        RIGHT_PARENTHESIS,
                        FUNCTION_SEPARATOR));

                put(BINARY_OPERATOR, EnumSet.of(
                        POSITIVE_NUMBER,
                        LEFT_PARENTHESIS,
                        FUNCTION));

                put(FUNCTION, EnumSet.of(
                        LEFT_PARENTHESIS_AFTER_FUNCTION));

                put(FUNCTION_SEPARATOR, EnumSet.of(
                        LEFT_PARENTHESIS,
                        NUMBER,
                        FUNCTION));

                put(LEFT_PARENTHESIS, EnumSet.of(
                        NUMBER,
                        LEFT_PARENTHESIS,
                        FUNCTION));

                put(LEFT_PARENTHESIS_AFTER_FUNCTION, EnumSet.of(
                        NUMBER,
                        LEFT_PARENTHESIS,
                        RIGHT_PARENTHESIS,
                        FUNCTION));

                put(RIGHT_PARENTHESIS, EnumSet.of(
                        BINARY_OPERATOR,
                        FINISH,
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
