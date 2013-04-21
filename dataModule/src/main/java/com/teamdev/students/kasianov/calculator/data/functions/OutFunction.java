package com.teamdev.students.kasianov.calculator.data.functions;

import javax.swing.*;
import java.math.BigDecimal;

public class OutFunction extends AbstractFunction {
    public OutFunction(int maximumArgumentsCount, int minimumArgumentsCount) {
        super(maximumArgumentsCount, minimumArgumentsCount);
    }

    @Override
    public BigDecimal calculate(BigDecimal... arguments) {
        for (BigDecimal argument : arguments) {
            JOptionPane.showMessageDialog(null, argument);
        }
        return null;
    }
}
