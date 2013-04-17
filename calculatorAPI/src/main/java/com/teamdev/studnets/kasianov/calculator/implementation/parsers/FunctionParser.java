package com.teamdev.studnets.kasianov.calculator.implementation.parsers;

import com.teamdev.students.kasianov.calculator.data.functions.Function;
import com.teamdev.students.kasianov.calculator.data.functions.FunctionProvider;
import com.teamdev.studnets.kasianov.calculator.implementation.ExpressionReader;
import com.teamdev.studnets.kasianov.calculator.implementation.commands.EvaluationCommand;
import com.teamdev.studnets.kasianov.calculator.implementation.commands.FunctionCommand;

import java.util.List;

public class FunctionParser implements MathParser {
    private final FunctionProvider functionProvider;

    public FunctionParser(FunctionProvider functionProvider) {
        this.functionProvider = functionProvider;
    }

    @Override
    public EvaluationCommand parse(ExpressionReader reader) {
        reader.skipSpaces();

        if (reader.isEndOfExpression()) {
            return null;
        }
        final int position = reader.getPosition();
        final String expression = reader.getExpression();
        int count = 0;
        Function function;

        while(!reader.isEndOfExpression()){
            if(!functionProvider.findFunction(expression.substring(position,position+ ++count))){
                break;
            }
        }

        if(--count > 0){
            function = functionProvider.getFunction(expression.substring(position, position + count));
            if (function != null) {
                reader.setPosition(position + count);
                return new FunctionCommand(function);
            }
        }

        return null;
    }
}
