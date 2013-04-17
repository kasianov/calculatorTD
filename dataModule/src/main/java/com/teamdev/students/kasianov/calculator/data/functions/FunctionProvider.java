package com.teamdev.students.kasianov.calculator.data.functions;

import java.util.HashMap;
import java.util.Map;

public final class FunctionProvider {
    private Map<String, Function> functionMap = new HashMap<String, Function>() {{
        put("sqrt", new SqrtFunction(1, 1));
        put("sum", new SumFunction(Integer.MAX_VALUE, 2));
        put("max", new MaxFunction(Integer.MAX_VALUE, 2));
        put("min", new MinFunction(Integer.MAX_VALUE, 2));
        put("pi", new PiFunction(0, 0));
    }};

    /**
     * @param functionName function name to find or part of the name from the beginning
     * @return - returns the first function if it is found by 'functionName', otherwise null
     */
    public boolean findFunction(String functionName) {
        for (String function : functionMap.keySet()) {
            if (function.startsWith(functionName)) {
                return true;
            }
        }
        return false;
    }

    public Function getFunction(String functionName) {
        for (String function : functionMap.keySet()) {
            if (function.equals(functionName)) {
                return functionMap.get(function);
            }
        }
        return null;
    }

    public Function createMainFunction(){
        return new MainFunction(1,1);
    }

}
