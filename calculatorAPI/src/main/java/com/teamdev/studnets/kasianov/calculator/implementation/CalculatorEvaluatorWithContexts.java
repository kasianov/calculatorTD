package com.teamdev.studnets.kasianov.calculator.implementation;

import com.teamdev.students.kasianov.calculator.data.functions.Function;
import com.teamdev.students.kasianov.calculator.data.functions.MainFunction;
import com.teamdev.students.kasianov.calculator.data.operators.BinaryOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;

public class CalculatorEvaluatorWithContexts implements Evaluator<MathExpressionException> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExpressionReader.class);
    private final Deque<CalculationContext> contexts = new ArrayDeque<CalculationContext>();
    private final CalculationContext topContext;

    {
        topContext = new CalculationContext(new FunctionElement(new MainFunction(1,1)));
        contexts.push(topContext);
    }

    @Override
    public void pushNumber(BigDecimal number) throws MathExpressionException {
        LOGGER.info("A number is being pushed to the context: " + number);
        contexts.peek().pushNumber(number);
    }

    @Override
    public void pushFunction(Function function) throws MathExpressionException {
        LOGGER.info("A function is being pushed to the context: " + function);
        contexts.push(new CalculationContext(new FunctionElement(function)));
    }

    @Override
    public void pushBinaryOperator(BinaryOperator binaryOperator) throws MathExpressionException {
        LOGGER.info("A binary operator is being pushed to the context: " + binaryOperator);
        contexts.peek().pushBinaryOperator(binaryOperator);
    }

    @Override
    public void pushLeftParenthesis() throws MathExpressionException {
        LOGGER.info("An opening bracket is being pushed to the context");
        contexts.peek().pushLeftParenthesis();
    }

    @Override
    public void pushRightParenthesis() throws MathExpressionException {
        LOGGER.info("A closing bracket is being pushed to the context");
        contexts.peek().pushRightParenthesis();
    }

    @Override
    public void pushFunctionSeparator() throws MathExpressionException {
        LOGGER.info("A function separator is being pushed to the context");
        contexts.peek().pushFunctionSeparator();
    }

    @Override
    public void popOutStack() throws MathExpressionException {
        while (contexts.size() > 1) {
            CalculationContext context = contexts.pop();
            contexts.peek().pushNumber(context.getResult());
        }
    }

    @Override
    public BigDecimal getResult() throws MathExpressionException {
        return contexts.pop().getResult();
    }

    private class CalculationContext {
        private final FunctionElement functionElement;
        private final Deque<BigDecimal> numberStack = new ArrayDeque<BigDecimal>();
        private final Deque<BinaryOperator> binaryOperatorStack = new ArrayDeque<BinaryOperator>();
        private final Deque<Integer> leftParenthesesStack = new ArrayDeque<Integer>();

        private CalculationContext(FunctionElement functionElement) {
            this.functionElement = functionElement;
        }

        public void pushNumber(BigDecimal number) throws MathExpressionException {
            //if the function's arguments count is 0
            //then try to increment it, and if it fails (doesn't take any arguments), then throw an exception
            if(functionElement.getArgumentsCount() == 0
                    && !functionElement.incrementArgumentCount()){

                throw new MathExpressionException("Wrong number of arguments in function");
            }
            numberStack.push(number);
        }

        public void pushBinaryOperator(BinaryOperator binaryOperator) {
            BinaryOperator topOperator;
            Integer topLeftParenthesis = leftParenthesesStack.peek();
            do {
                //to make it impossible to keep peeking operators through opening parenthesis
                if (topLeftParenthesis != null && binaryOperatorStack.size() == topLeftParenthesis) {
                    break;
                }
                topOperator = binaryOperatorStack.peek();
                if (topOperator != null && binaryOperator.compareTo(topOperator) < 1) {
                    executeBinaryOperator(topOperator);
                    binaryOperatorStack.pop();
                    continue;
                }
                break;
            } while (true);

            binaryOperatorStack.push(binaryOperator);
        }

        public void pushFunction() throws MathExpressionException{
            if(functionElement.getArgumentsCount() == 0
                    && !functionElement.incrementArgumentCount()){

                throw new MathExpressionException("Wrong number of arguments in function");
            }
        }

        public void pushLeftParenthesis() throws MathExpressionException {
            leftParenthesesStack.push(binaryOperatorStack.size());
        }

        public void pushRightParenthesis() throws MathExpressionException {
            if (leftParenthesesStack.isEmpty()) {
                throw new MathExpressionException("Mismatched right parenthesis");
            }
            Integer topLeftParenthesis = leftParenthesesStack.pop();
            //pop out and executed all binary operators which were added after the last opening parenthesis
            while (binaryOperatorStack.size() > topLeftParenthesis) {
                executeBinaryOperator(binaryOperatorStack.pop());
            }
            //true means that it is the closing parenthesis of a function which is on the top of the functions stack
            if (leftParenthesesStack.isEmpty() && this != topContext) {
                contexts.remove(this);
                contexts.peek().pushNumber(getResult());
            }

        }

        public void pushFunctionSeparator() throws MathExpressionException {
            //there is always the opening (only one) parenthesis before function separator
            //there is always a parent context
            if (leftParenthesesStack.size() != 1 || this == topContext) {
                throw new MathExpressionException("Function separator is not in a function parentheses");
            }
            //each separator means +1 argument to a function, so check if it can take one more
            if (!functionElement.incrementArgumentCount()) {
                throw new MathExpressionException("Wrong number of arguments");
            }
            //get an intermediate result and push it to the parent context
            //after this numberStack and binaryOperatorsStack are empty, so the next arguments expression
            //is precessed as new one, only leftParenthesesStack contains one element which is the opening
            //parenthesis of the function
            popOperatorsStack();
        }

        public BigDecimal getResult() throws MathExpressionException {
            if (!leftParenthesesStack.isEmpty()) {
                throw new MathExpressionException("Mismatched parentheses");
            }
            if(!binaryOperatorStack.isEmpty()){
                popOperatorsStack();
            }
            executeFunction(functionElement);
            return numberStack.pop();
        }

        public void popOperatorsStack() {
            while (!binaryOperatorStack.isEmpty()) {
                executeBinaryOperator(binaryOperatorStack.pop());
            }
        }

        private void executeBinaryOperator(BinaryOperator binaryOperator) {
            BigDecimal rightOperand = numberStack.pop();
            BigDecimal leftOperand = numberStack.pop();
            numberStack.push(binaryOperator.calculate(leftOperand, rightOperand));
        }

        private void executeFunction(FunctionElement functionElement) {
            BigDecimal[] arguments = new BigDecimal[functionElement.getArgumentsCount()];
            for (int i = arguments.length - 1; i >= 0; --i) {
                arguments[i] = numberStack.pop();
            }
            numberStack.push(functionElement.getFunction().calculate(arguments));
        }

    }

}
