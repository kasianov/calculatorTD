package com.teamdev.studnets.kasianov.calculator.implementation;

import com.teamdev.students.kasianov.calculator.data.functions.Function;

class FunctionElement {
    private final Function function;
    private int argumentsCount;

    FunctionElement(Function function) {
        this.function = function;
    }

    public int getArgumentsCount() {
        return argumentsCount;
    }

    public Function getFunction() {
        return function;
    }

    public boolean incrementArgumentCount() {
        if(argumentsCount + 1 <= function.getMaximumArgumentsCount()){
            ++argumentsCount;
            return true;
        }
        return false;
    }

    public boolean checkArgumentsCount() {
        return argumentsCount >= function.getMinimumArgumentsCount()
                && argumentsCount <= function.getMaximumArgumentsCount();
    }

}
