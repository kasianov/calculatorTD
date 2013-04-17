package com.teamdev.studnets.kasianov.calculator.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Service class for EvaluationContext
 * Represents a math expression holder and provides operations to read the expression
 * and move current read position
 */
public class ExpressionReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExpressionReader.class);
    final private String expression;
    private int position;
    private int previousPositionOffset;
    private List<Character> charactersToSkip = new ArrayList<Character>(){{
        Collections.addAll(this,' ','\t','\n','\r');
    }};

    public ExpressionReader(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }

    public int getPosition() {
        return position;
    }

    public void skipSpaces(){
        while(!isEndOfExpression() && charactersToSkip.contains(expression.charAt(position))){
            setPosition(++position);
        }
        previousPositionOffset = 0;
    }

    /**
     * Each time equates the difference between new position and last position to 'previousPositionOffset'
     * which is necessary in order to provide rollback operation of current position to previous one
     *
     * @param position - new current position
     */
    public void setPosition(int position) {
        LOGGER.info("The current read position is set from: "
                + this.position + " to: " + position );
        previousPositionOffset = position - this.position;
        this.position = position;
    }

    /**
     * Sets previous position as current position
     */
    public void rollbackPosition() {
        position -= previousPositionOffset;
        previousPositionOffset = 0;
    }

    /**
     * Checks if the current position is at the end of the expression
     *
     * @return true if the current position is at the end of the expression, otherwise false
     */
    public boolean isEndOfExpression() {
        return position >= expression.length();
    }

    /**
     * Checks if the current position + 'charNumber' is at the end of the expression
     *
     * @param charNumber - value to add to the current position
     * @return true if the checking position is at the end of the expression, otherwise false
     */
    public boolean isEndOfExpression(int charNumber) {
        return position + charNumber >= expression.length();
    }
}
