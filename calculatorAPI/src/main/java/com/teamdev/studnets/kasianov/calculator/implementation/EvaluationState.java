package com.teamdev.studnets.kasianov.calculator.implementation;

public enum EvaluationState {
    START,
    BINARY_OPERATOR,
    FUNCTION,
    LEFT_PARENTHESIS,
    LEFT_PARENTHESIS_AFTER_FUNCTION,
    RIGHT_PARENTHESIS,
    FUNCTION_SEPARATOR,
    NUMBER,
    POSITIVE_NUMBER,
    FINISH
}
