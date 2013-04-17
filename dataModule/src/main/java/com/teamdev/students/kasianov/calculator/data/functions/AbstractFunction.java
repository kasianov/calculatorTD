package com.teamdev.students.kasianov.calculator.data.functions;

abstract class AbstractFunction implements Function{
    private int minimumArgumentsCount;
    private int maximumArgumentsCount;
    private String stringRepresentation;

    protected AbstractFunction(int maximumArgumentsCount, int minimumArgumentsCount) {
        this.maximumArgumentsCount = maximumArgumentsCount;
        this.minimumArgumentsCount = minimumArgumentsCount;
        this.stringRepresentation = this.getClass().getSimpleName()
                + ": min args=" + minimumArgumentsCount
                + ", max args=" + maximumArgumentsCount;
    }

    @Override
    public int getMaximumArgumentsCount() {
        return maximumArgumentsCount;
    }

    @Override
    public int getMinimumArgumentsCount() {
        return minimumArgumentsCount;
    }

    @Override
    public String toString(){
        return stringRepresentation;
    }
}
