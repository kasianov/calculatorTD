package com.teamdev.students.calculator.swingui;


import com.teamdev.studnets.kasianov.calculator.implementation.Calculator;
import com.teamdev.studnets.kasianov.calculator.implementation.EvaluationException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;

public class CalculatorFrame extends JFrame {
    private JTextArea textExpression;
    private Calculator calculator;

    public CalculatorFrame(Calculator calculator) {
        this.calculator = calculator;
        initializeComponents();
    }

    private void initializeComponents() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Calculator");
        setSize(new Dimension(326, 320));
        setResizable(false);

        JPanel panel = new JPanel(null);
        setContentPane(panel);

        JLabel labelExpression = new JLabel("Code");
        labelExpression.setLocation(10, 10);
        labelExpression.setSize(300, 20);

        textExpression = new JTextArea();
        textExpression.setMargin(new Insets(5,5,5,5));
        JScrollPane jScrollPane = new JScrollPane(textExpression);
        jScrollPane.setSize(new Dimension(300,220));
        jScrollPane.setLocation(10, 30);

        JButton buttonResult = new JButton("Execute");
        buttonResult.setLocation(10, 260);
        buttonResult.setSize(new Dimension(300, 20));
        buttonResult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonResultClicked(e);
            }
        });

        panel.add(labelExpression);
        panel.add(jScrollPane);
        panel.add(buttonResult);

        //getRootPane().setDefaultButton(buttonResult);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        setBounds(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2, getWidth(), getHeight());
        setVisible(true);

        getRootPane().getActionMap().put("exe", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonResultClicked(e);
            }
        });
        getRootPane().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ALT | KeyEvent.VK_ENTER,-1),"exe");
    }

    private void buttonResultClicked(ActionEvent event) {
        if (calculator != null) {
            try {
                textExpression.setText(textExpression.getText());
                calculator.calculate(textExpression.getText());
            } catch (EvaluationException ex) {
                String info = ex.getMessage()
                        + "\nPosition: " + ex.getPosition();
                JOptionPane.showMessageDialog(null, info);
                textExpression.grabFocus();
                textExpression.setCaretPosition(ex.getPosition());
            }
        }
    }
}
