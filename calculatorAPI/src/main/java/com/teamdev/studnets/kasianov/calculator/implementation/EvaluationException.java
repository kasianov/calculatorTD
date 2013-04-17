package com.teamdev.studnets.kasianov.calculator.implementation;

public class EvaluationException extends Exception {
    private int position;

    public EvaluationException(String message, int position) {
        super(message);
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return getMessage() + " Position: " + getPosition();
    }
}
