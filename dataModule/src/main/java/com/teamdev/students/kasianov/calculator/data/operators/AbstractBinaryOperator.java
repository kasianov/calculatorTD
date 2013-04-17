package com.teamdev.students.kasianov.calculator.data.operators;

public abstract class AbstractBinaryOperator implements BinaryOperator {

    private final Priority priority;
    private String stringRepresentation;

    protected AbstractBinaryOperator(Priority priority) {
        if (priority == null) {
            throw new NullPointerException("Null priority passed");
        }
        this.priority = priority;
        stringRepresentation = getClass().getSimpleName()
                + ": priority=" + priority;
    }

    @Override
    public int compareTo(BinaryOperator o) {
        return priority.compareTo(((AbstractBinaryOperator) (o)).priority);
    }

    @Override
    public String toString() {
        return stringRepresentation;
    }

    public static enum Priority {
        LOW, //+,-
        MEDIUM, //*,/
        HIGH //^
    }
}
