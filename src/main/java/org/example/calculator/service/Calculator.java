package org.example.calculator.service;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    private final OperationService service = new OperationService();
    private final List<Character> operators;

    public Calculator() {
        operators = new ArrayList<>();
        operators.add('+');
        operators.add('-');
        operators.add('×');
        operators.add('÷');
    }

    public String calculate(String s) {
        if (s.isEmpty()) {
            return "0";
        }
        String expression = s.replace(',', '.');

        int operatorIndex = -1;
        char foundOperator = ' ';

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (operators.contains(c)) {
                if (c == '-' && i == 0) continue;

                operatorIndex = i;
                foundOperator = c;
                break;
            }
        }

        if (operatorIndex == -1) {
            return s;
        }

        try {
            double num1 = Double.parseDouble(expression.substring(0, operatorIndex));
            double num2 = Double.parseDouble(expression.substring(operatorIndex + 1));

            double result = 0.0;

            try {
                switch (String.valueOf(foundOperator)) {
                    case "+":
                        result = service.addition(num1, num2);
                        break;
                    case "-":
                        result = service.subtraction(num1, num2);
                        break;
                    case "×":
                        result = service.multiplication(num1, num2);
                        break;
                    case "÷":
                        result = service.division(num1, num2);
                        if (Double.isInfinite(result) || Double.isNaN(result)) {
                            return "Error: Division by zero";
                        }
                        break;
                    default:
                        return "Error: Invalid operator";
                }
            } catch (ArithmeticException e) {
                return "Error: Division by zero";
            }
            return String.valueOf(result);
        } catch (NumberFormatException e) {
            return "Error: Invalid input format";
        }
    }

    public String reciprocal(String s) {
        try {
            double num = Double.parseDouble(s.replace(',', '.'));
            double result = service.reciprocal(num);
            return String.valueOf(result);
        } catch (NumberFormatException | ArithmeticException e) {
            return "Error";
        }
    }

    public String square(String s) {
        try {
            double num = Double.parseDouble(s.replace(',', '.'));
            double result = service.square(num);
            return String.valueOf(result);
        } catch (NumberFormatException e) {
            return "Error";
        }
    }

    public String squareRoot(String s) {
        try {
            double num = Double.parseDouble(s.replace(',', '.'));
            double result = service.squareRoot(num);
            return String.valueOf(result);
        } catch (NumberFormatException | ArithmeticException e) {
            return "Error";
        }
    }

    public String toPercentage(String s) {
        try {
            double num = Double.parseDouble(s.replace(',', '.'));
            double result = service.calculatePercentage(num);
            return String.valueOf(result);
        } catch (NumberFormatException e) {
            return "Error";
        }
    }

    public List<Character> getOperators() {
        return operators;
    }
}
