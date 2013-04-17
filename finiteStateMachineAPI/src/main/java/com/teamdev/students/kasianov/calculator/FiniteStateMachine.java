package com.teamdev.students.kasianov.calculator;

public interface FiniteStateMachine<State extends Enum,
        Context extends FiniteStateMachineContext<State, ?, ?>,
        Matrix extends TransitionMatrix<State>,
        Recognizer extends StateRecognizer<State, Context>,
        TransitionError extends Exception> {

    void run(Context context) throws TransitionError;

    Matrix getTransitionMatrix();

    Recognizer getStateRecognizer();

    void deadlock(Context context) throws TransitionError;

    boolean moveForward(Context context);
}
