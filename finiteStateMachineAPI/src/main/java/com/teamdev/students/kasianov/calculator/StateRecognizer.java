package com.teamdev.students.kasianov.calculator;


public interface StateRecognizer<State extends Enum, Context> {

    boolean accept(State state, Context context);
}
