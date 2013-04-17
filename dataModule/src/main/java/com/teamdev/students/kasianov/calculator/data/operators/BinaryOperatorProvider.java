package com.teamdev.students.kasianov.calculator.data.operators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.teamdev.students.kasianov.calculator.data.operators.AbstractBinaryOperator.Priority;

public final class BinaryOperatorProvider {
    private Map<String, BinaryOperator> operatorMap = new HashMap<String, BinaryOperator>() {{
        put("+", new PlusBinaryOperator(Priority.LOW));
        put("-", new MinusBinaryOperator(Priority.LOW));
        put("*", new MultiplyBinaryOperator(Priority.MEDIUM));
        put("/", new DivideBinaryOperator(Priority.MEDIUM));
        put("^", new PowerBinaryOperator(Priority.HIGH));
    }};

    /**
     * @param operatorName - binary operator name to find or a part of the name from beginning
     * @return - return first matched binary operator or null, if there is no match
     */
    public boolean findOperand(String operatorName) {
        for (String operator : operatorMap.keySet()) {
            if (operator.startsWith(operatorName)) {
                return true;
            }
        }
        return false;
    }

    public BinaryOperator getBinaryOperator(String operatorName){
        for (String operator : operatorMap.keySet()) {
            if (operator.startsWith(operatorName)) {
                return operatorMap.get(operator);
            }
        }
        return null;
    }
}
