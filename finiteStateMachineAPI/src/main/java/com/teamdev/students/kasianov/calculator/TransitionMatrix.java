package com.teamdev.students.kasianov.calculator;

import java.util.Set;

public interface TransitionMatrix<State extends Enum> {

    Set<State> getPossibleStates(State state);

    boolean isFinishState(State state);

    State getStartState();

    Set<State> getRestStates(Set<State> states);
}
