package com.teamdev.studnets.kasianov.calculator.implementation.parsers;

import com.teamdev.studnets.kasianov.calculator.implementation.ExpressionReader;
import com.teamdev.studnets.kasianov.calculator.implementation.commands.EvaluationCommand;
import com.teamdev.studnets.kasianov.calculator.implementation.commands.NumberCommand;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParsePosition;


public class NumberParser implements MathParser {
    private NumberFormat numberFormat;

    public NumberParser(NumberFormat numberFormat) {
        this.numberFormat = numberFormat;
    }

    public static NumberFormat createNumberFormat() {
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        DecimalFormatSymbols decimalFormatSymbols = DecimalFormatSymbols.getInstance();
        decimalFormatSymbols.setDecimalSeparator('.');
        decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
        return decimalFormat;
    }

    public static NumberFormat createNumberFormatOnlyPositive() {
        DecimalFormat decimalFormat = new DecimalFormat("-0.0");
        decimalFormat.setPositivePrefix("");
        DecimalFormatSymbols decimalFormatSymbols = DecimalFormatSymbols.getInstance();
        decimalFormatSymbols.setDecimalSeparator('.');
        decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
        return decimalFormat;
    }

    @Override
    public EvaluationCommand parse(ExpressionReader reader) {
        reader.skipSpaces();

        final ParsePosition position =
                new ParsePosition(reader.getPosition());

        final Number number =
                numberFormat.parse(reader.getExpression(), position);

        if (position.getErrorIndex() < 0) {

            final int readPosition = position.getIndex();
            reader.setPosition(readPosition);

            return new NumberCommand(new BigDecimal(number.doubleValue()));
        }

        return null;
    }
}
