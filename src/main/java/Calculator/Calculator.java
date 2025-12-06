package Calculator;

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
            float num1 = Float.parseFloat(expression.substring(0, operatorIndex));
            float num2 = Float.parseFloat(expression.substring(operatorIndex + 1));

            float result = 0.0f;

            try {
                switch (String.valueOf(foundOperator)) {
                    case "+":
                        result = (float)service.addition(num1, num2);
                        break;
                    case "-":
                        result = (float)service.subtraction(num1, num2);
                        break;
                    case "×":
                        result = (float)service.multiplication(num1, num2);
                        break;
                    case "÷":
                        result = (float)service.division(num1, num2);
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

    public List<Character> getOperators() {
        return operators;
    }
}
