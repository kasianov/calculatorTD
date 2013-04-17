package com.teamdev.students.kasianov.calculator;

import com.teamdev.students.calculator.swingui.CalculatorFrame;
import com.teamdev.students.kasianov.calculator.consoleui.CalculatorConsole;
import com.teamdev.studnets.kasianov.calculator.implementation.Calculator;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("-console")) {
            new CalculatorConsole(Calculator.createCalculator()).run();
        } else {
            new CalculatorFrame(Calculator.createCalculator());
        }
    }
}
