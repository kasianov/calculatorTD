package com.teamdev.studnets.kasianov.calculator.implementation;

import com.teamdev.students.kasianov.calculator.StateRecognizer;
import com.teamdev.students.kasianov.calculator.data.functions.FunctionProvider;
import com.teamdev.students.kasianov.calculator.data.operators.BinaryOperatorProvider;
import com.teamdev.studnets.kasianov.calculator.implementation.commands.EvaluationCommand;
import com.teamdev.studnets.kasianov.calculator.implementation.parsers.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumMap;
import java.util.Map;

import static com.teamdev.studnets.kasianov.calculator.implementation.EvaluationState.*;


public class EvaluationStateRecognizer implements StateRecognizer<EvaluationState, EvaluationContext> {
    private static final Logger LOGGER = LoggerFactory.getLogger(EvaluationStateRecognizer.class);

    private final Map<EvaluationState, MathParser> stateMathParserMap =
            new EnumMap<EvaluationState, MathParser>(EvaluationState.class) {{
                put(NUMBER, new NumberParser(NumberParser.createNumberFormat()));
                put(POSITIVE_NUMBER, new NumberParser(NumberParser.createNumberFormatOnlyPositive()));
                put(BINARY_OPERATOR, new BinaryOperatorParser(new BinaryOperatorProvider()));
                put(FUNCTION, new FunctionParser(new FunctionProvider()));
                put(LEFT_PARENTHESIS, new LeftParenthesisParser());
                put(LEFT_PARENTHESIS_AFTER_FUNCTION, new LeftParenthesisParser());
                put(RIGHT_PARENTHESIS, new RightParenthesisParser());
                put(FUNCTION_SEPARATOR, new FunctionSeparatorParser());
                put(FINISH, new EndOfExpressionParser());
            }};

    @Override
    public boolean accept(EvaluationState evaluationState, EvaluationContext context) {
        LOGGER.info("Attempt to accept a state: " + evaluationState);
        final MathParser parser = stateMathParserMap.get(evaluationState);

        if (parser == null) {
            throw new NullPointerException("There is no parser for state: " + evaluationState);
        }

        LOGGER.info("Parser for the state: " + parser);

        EvaluationCommand command = parser.parse(context.getExpressionReader());
        if (command != null) {
            LOGGER.info("The state is accepted, a command to be executed is " + command);
            command.perform(context);
            return true;
        }
        LOGGER.info("The state is not accepted");
        return false;
    }
}
