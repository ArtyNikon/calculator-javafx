package org.example.calculator.сontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import org.example.calculator.service.Calculator;

public class Controller {

    @FXML
    Text txt;

    private double storedValue = 0.0;
    private String pendingOperator = "";
    private boolean isNewNumber = true;

    private boolean isOpPressed = false;
    private boolean isEqualsPressed = false;
    private double lastOperand = 0.0;
    private String lastOperator = "";

    private Calculator calculator = new Calculator();

    public void handleDigit(ActionEvent event) {
        String digit = getButtonName(event);
        String currentText = txt.getText();

        isOpPressed = false;
        isEqualsPressed = false;

        if (currentText.equals("0") || isNewNumber) {
            txt.setText(digit);
            isNewNumber = false;
        } else {
            txt.setText(currentText + digit);
        }
    }

    public void handleMultiply(ActionEvent event) {
        handleOperator(event);
    }

    public void handleSubtract(ActionEvent event) {
        handleOperator(event);
    }

    public void handleAdd(ActionEvent event) {
        handleOperator(event);
    }

    public void handleDivide(ActionEvent event) {
        handleOperator(event);
    }

    private void handleOperator(ActionEvent event) {
        String operator = getButtonName(event);
        String currentText = txt.getText();

        if (currentText.contains("Error")) return;

        if (isOpPressed) {
            pendingOperator = operator;
            return;
        }

        double currentNumber = Double.parseDouble(currentText.replace(',', '.'));

        if (!pendingOperator.isEmpty()) {
            String expression = Double.toString(storedValue) + pendingOperator + Double.toString(currentNumber);
            String result = calculator.calculate(expression);

            if (result.contains("Error")) {
                txt.setText(result);
                return;
            }
            storedValue = Double.parseDouble(result);

            String displayResult = String.valueOf(storedValue);
            if (displayResult.endsWith(".0")) displayResult = displayResult.substring(0, displayResult.length() - 2);
            txt.setText(displayResult.replace('.', ','));
        } else {
            storedValue = currentNumber;
        }

        pendingOperator = operator;
        isNewNumber = true;
        isOpPressed = true;
        isEqualsPressed = false;
    }

    public void handleClearAll(ActionEvent event) {
        txt.setText("0");
        storedValue = 0.0;
        pendingOperator = "";
        isNewNumber = true;
        isOpPressed = false;
        isEqualsPressed = false;
        lastOperand = 0.0;
        lastOperator = "";
    }

    public void handleClearEntry(ActionEvent event) {
        if (isEqualsPressed) {
            handleClearAll(event);
            return;
        }

        String text = txt.getText();
        if (text.equals("0") || isNewNumber) {
            txt.setText("0");
            return;
        }

        txt.setText("0");
        isNewNumber = true;
    }

    public void handleBackspace(ActionEvent event) {
        if (isNewNumber || isEqualsPressed) {
            return;
        }

        String currentText = txt.getText();

        if (currentText.length() <= 1) {
            txt.setText("0");
            isNewNumber = true;
            return;
        }

        String newText = currentText.substring(0, currentText.length() - 1);
        txt.setText(newText);
    }

    public void handleDecimalPoint(ActionEvent event) {
        if (isNewNumber || isEqualsPressed) {
            txt.setText("0,");
            isNewNumber = false;
            isEqualsPressed = false;
            isOpPressed = false;
            return;
        }

        String currentText = txt.getText();
        if (!currentText.contains(",")) {
            txt.setText(currentText + ",");
        }
    }

    public void handleEquals(ActionEvent event) {
        if (txt.getText().contains("Error")) return;

        double currentNumber = Double.parseDouble(txt.getText().replace(',', '.'));
        String result;

        if (pendingOperator.isEmpty() && !isEqualsPressed) {
            return;
        }

        if (!pendingOperator.isEmpty()) {
            lastOperand = currentNumber;
            lastOperator = pendingOperator;

            String expression = Double.toString(storedValue) + pendingOperator + Double.toString(currentNumber);
            result = calculator.calculate(expression);

            pendingOperator = "";
        } else {
            String expression = Double.toString(storedValue) + lastOperator + Double.toString(lastOperand);
            result = calculator.calculate(expression);
        }

        if (result.contains("Error")) {
            txt.setText(result);
            return;
        }

        storedValue = Double.parseDouble(result);

        String displayResult = String.valueOf(storedValue);
        if (displayResult.endsWith(".0")) {
            displayResult = displayResult.substring(0, displayResult.length() - 2);
        }

        txt.setText(displayResult.replace('.', ','));
        isNewNumber = true;
        isOpPressed = false;
        isEqualsPressed = true;
    }

    public void handleReciprocal(ActionEvent event) {
        String currentText = txt.getText();
        String result = calculator.reciprocal(currentText);

        if (!result.contains("Error")) {
            result = result.replace('.', ',');
            storedValue = Double.parseDouble(result.replace(',', '.'));
        }

        txt.setText(result);
        isNewNumber = true;
        isEqualsPressed = true;
    }

    public void handleSquare(ActionEvent event) {
        String currentText = txt.getText();
        String result = calculator.square(currentText);

        if (!result.contains("Error")) {
            result = result.replace('.', ',');
            storedValue = Double.parseDouble(result.replace(',', '.'));
        }

        txt.setText(result);
        isNewNumber = true;
        isEqualsPressed = true;
    }

    public void handleSquareRoot(ActionEvent event) {
        String currentText = txt.getText();
        String result = calculator.squareRoot(currentText);

        if (!result.contains("Error")) {
            result = result.replace('.', ',');
            storedValue = Double.parseDouble(result.replace(',', '.'));
        }

        txt.setText(result);
        isNewNumber = true;
        isEqualsPressed = true;
    }

    public void handlePercent(ActionEvent event) {
        String currentText = txt.getText();
        if (currentText.contains("Error")) return;

        double currentNumber = Double.parseDouble(currentText.replace(',', '.'));
        double resultValue;

        if (!pendingOperator.isEmpty()) {
            resultValue = storedValue * (currentNumber / 100.0);
        } else {
            resultValue = currentNumber / 100.0;
        }

        String resultString = String.valueOf(resultValue);
        if (resultString.endsWith(".0")) {
            resultString = resultString.substring(0, resultString.length() - 2);
        }

        txt.setText(resultString.replace('.', ','));
    }

    public void handleToggleSign(ActionEvent event) {
        String currentText = txt.getText();
        if (currentText.isEmpty() || currentText.equals("0") || currentText.equals("0,")) return;

        try {
            double val = Double.parseDouble(currentText.replace(',', '.'));
            double result = -val;

            String resultString = String.valueOf(result);

            if (resultString.endsWith(".0")) {
                resultString = resultString.substring(0, resultString.length() - 2);
            }

            txt.setText(resultString.replace('.', ','));

            if (isEqualsPressed) {
                storedValue = result;
            }

        } catch (NumberFormatException e) {
        }
    }

    public String getButtonName(ActionEvent event) {
        return ((Button) event.getSource()).getText();
    }

    private boolean isLastCharOperator(String text) {
        if (text.isEmpty()) {
            return false;
        }
        char lastChar = text.charAt(text.length() - 1);
        return calculator.getOperators().contains(lastChar);
    }
}
