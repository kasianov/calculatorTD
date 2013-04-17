package com.teamdev.studnets.kasianov.calculator.implementation;

import com.teamdev.students.kasianov.calculator.data.functions.Function;
import com.teamdev.students.kasianov.calculator.data.operators.BinaryOperator;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class CalculatorEvaluator implements Evaluator<MathExpressionException> {

    private final String functionBaseName = "function";
    private final String binaryOperatorBaseName = "binaryOperator";
    private final String leftParenthesis = "leftParenthesis";
    private final Deque<BigDecimal> numberStack = new LinkedList<BigDecimal>();
    private final Deque<FunctionElement> functionStack = new LinkedList<FunctionElement>();
    private final Deque<BinaryOperator> binaryOperatorStack = new LinkedList<BinaryOperator>();
    private final Deque<String> operatorStack = new ArrayDeque<String>();

    /**
     * Gets a result from passed binary operator and pushes it onto the number stack
     *
     * @param binaryOperator - operator to get result from
     */
    private void executeBinaryOperator(BinaryOperator binaryOperator) {
        BigDecimal rightOperand = numberStack.pop();
        BigDecimal leftOperand = numberStack.pop();
        numberStack.push(binaryOperator.calculate(leftOperand, rightOperand));
    }

    /**
     * Get a result from passed function and pushes it onto the number stack
     *
     * @param functionElement - function to get result from
     */
    private void executeFunction(FunctionElement functionElement) {
        BigDecimal[] arguments = new BigDecimal[functionElement.getArgumentsCount()];
        for (int i = arguments.length - 1; i >= 0; --i) {
            arguments[i] = numberStack.pop();
        }
        numberStack.push(functionElement.getFunction().calculate(arguments));
    }

    @Override
    /**
     * Pushes the number onto the number stack and if there is a function stack element
     * at the top of the function stack, then checks if its argument count equals 0 and if it is,
     * checks if the function can take one argument, if no, then an exception is thrown
     */
    public void pushNumber(BigDecimal number) throws MathExpressionException {
        numberStack.push(number);
        FunctionElement topFunction = functionStack.peek();
        if (topFunction != null
                && topFunction.getArgumentsCount() == 0
                && !topFunction.incrementArgumentCount()) {

            throw new MathExpressionException("Wrong number of arguments in function");
        }
    }

    @Override
    /**
     * Pushes the function onto the function stack and operator stack,
     * if there was a function stack element at the top of the function stack,
     * then checks if its argument count equals to 0 and if it is,
     * checks if the function can take one argument, if no, then an exception is thrown
     */
    public void pushFunction(Function function) throws MathExpressionException {
        FunctionElement topFunction = functionStack.peek();
        if (topFunction != null
                && topFunction.getArgumentsCount() == 0
                && !topFunction.incrementArgumentCount()) {

            throw new MathExpressionException("Wrong number of arguments in function");
        }
        operatorStack.push(functionBaseName);
        functionStack.push(new FunctionElement(function));
    }

    @Override
    /**
     * Pops out all binary operators from binary operator stack til the top element is not null and its precedence
     * greater or equal to a precedence of the passed binary operator, after the next element is popped out
     * the result of the binary operator is pushed onto the number stack by invoking 'executeBinaryOperator'
     * After all pushed passed binary operator onto the binary operator and operator stacks
     */
    public void pushBinaryOperator(BinaryOperator binaryOperator) throws MathExpressionException {
        if (!operatorStack.isEmpty()) {
            String topElement = operatorStack.peek();

            while (topElement != null) {

                if (topElement.equals(binaryOperatorBaseName)) {
                    BinaryOperator topBinaryOperator = binaryOperatorStack.peek();
                    if (topBinaryOperator != null && binaryOperator.compareTo(topBinaryOperator) < 1) {
                        executeBinaryOperator(topBinaryOperator);
                        binaryOperatorStack.pop();
                        operatorStack.pop();
                        topElement = operatorStack.peek();
                        continue;
                    }
                }
                break;
            }
        }

        operatorStack.push(binaryOperatorBaseName);
        binaryOperatorStack.push(binaryOperator);
    }

    @Override
    /**
     * Pushes a left parenthesis onto the operator stack
     */
    public void pushLeftParenthesis() throws MathExpressionException {
        operatorStack.push(leftParenthesis);
    }

    @Override
    /**
     * Pops out the operator stack till the top element is a left parenthesis.
     * If the next element is a binary operator, it gets a result and pushes it onto the number stack
     * If the next element is a left parenthesis it is popped out and if the next element after that is a function,
     * then checks if it has right arguments count and if it is,
     * then gets a result of the function and pushes it onto the number stack
     * if the arguments count is wrong, then an exception is thrown
     * If the operator stack got empty without finding a left parenthesis, then there is a mismatched
     * right parenthesis so an exception is thrown
     */
    public void pushRightParenthesis() throws MathExpressionException {
        String topOperator = operatorStack.peek();
        while (topOperator != null) {

            if (topOperator.equals(leftParenthesis)) {
                operatorStack.pop();
                topOperator = operatorStack.peek();
                if (topOperator != null && topOperator.equals(functionBaseName)) {
                    operatorStack.pop();
                    FunctionElement topFunctionElement = functionStack.pop();
                    if (topFunctionElement.checkArgumentsCount()) {
                        executeFunction(topFunctionElement);
                    } else {
                        throw new MathExpressionException("Wrong number of function arguments");
                    }
                }
                return;
            } else if (topOperator.equals(binaryOperatorBaseName)) {
                BinaryOperator binaryOperator = binaryOperatorStack.pop();
                executeBinaryOperator(binaryOperator);
            } else {
                throw new IllegalArgumentException(
                        "Illegal element at the top of the operators stack while pushing right parenthesis ("
                                + topOperator + ")");
            }

            operatorStack.pop();
            topOperator = operatorStack.peek();

        }

        throw new MathExpressionException("Mismatch right parenthesis");

    }

    @Override
    public BigDecimal getResult() throws MathExpressionException {
        return numberStack.pop();
    }

    @Override
    /**
     *
     */
    public void pushFunctionSeparator() throws MathExpressionException {
        String topOperator = operatorStack.peek();

        while (topOperator != null) {

            if (topOperator.equals(binaryOperatorBaseName)) {
                executeBinaryOperator(binaryOperatorStack.pop());
            } else if (topOperator.equals(leftParenthesis)) {
                //pop out the left parenthesis to find out which element goes after that
                operatorStack.pop();
                String operatorAfterLeftParenthesis = operatorStack.peek();
                if (operatorAfterLeftParenthesis == null || !operatorAfterLeftParenthesis.equals(functionBaseName)) {
                    throw new MathExpressionException("The function separator is not in a function parentheses");
                }
                //push the left parenthesis back
                operatorStack.push(topOperator);
                FunctionElement topFunctionElement = functionStack.peek();
                if (topFunctionElement != null) {
                    if (!topFunctionElement.incrementArgumentCount()) {
                        throw new MathExpressionException("Wrong number of function arguments");
                    }
                    //everything is good (yet)
                    return;
                } else {
                    //to throw an exception after loop
                    break;
                }

            } else {
                throw new IllegalArgumentException(
                        "Illegal element at the top of the operators stack while pushing function separator ("
                                + topOperator + ")");
            }

            operatorStack.pop();
            topOperator = operatorStack.peek();

        }

        throw new MathExpressionException("Misplaced function separator");
    }

    @Override
    public void popOutStack() throws MathExpressionException {
        while (!operatorStack.isEmpty()) {
            String topElement = operatorStack.pop();
            if (topElement.equals(leftParenthesis)) {
                throw new MathExpressionException("There is a mismatched parentheses in the expression.");
            }
            executeBinaryOperator(binaryOperatorStack.pop());
        }
        if (numberStack.size() != 1) {
            throw new MathExpressionException("Result number stack contains more than one value.");
        }
    }

}
