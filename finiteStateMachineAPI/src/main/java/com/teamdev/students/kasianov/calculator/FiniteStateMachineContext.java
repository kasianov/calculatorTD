package com.teamdev.students.kasianov.calculator;

public interface FiniteStateMachineContext<State extends Enum,
        Result,
        ResultException extends Exception> {

    State getState();

    void setState(State state);

    Result getResult() throws ResultException;

    boolean isInErrorState();

    void setInErrorState();

}
