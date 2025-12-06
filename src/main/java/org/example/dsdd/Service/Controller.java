package org.example.dsdd.Service;

import Calculator.Calculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class Controller {

    @FXML
    Text txt;

    private Calculator calculator = new Calculator();

    public void handleDigit(ActionEvent event) throws IOException {
        String digit = getButtonName(event);
        String currentText = txt.getText();

        if (currentText.equals("0")) {
            txt.setText(digit);
        } else {
            txt.setText(currentText + digit);
        }
    }

    public void handleMultiply(ActionEvent event) throws IOException {
        handleOperator(event);
    }

    public void handleSubtract(ActionEvent event) throws IOException {
        handleOperator(event);
    }

    public void handleAdd(ActionEvent event) throws IOException {
        handleOperator(event);
    }

    public void handleDivide(ActionEvent event) throws IOException {
        handleOperator(event);
    }

    private void handleOperator(ActionEvent event) {
        String operator = getButtonName(event);
        String currentText = txt.getText();

        if (currentText.isEmpty()) {
            return;
        }

        if (isLastCharOperator(currentText)) {
            if (currentText.equals("-")) {
                return;
            }

            String oldOperator = currentText.substring(currentText.length() - 1);

            if (operator.equals(oldOperator)) {
                return;
            }

            String newText = currentText.substring(0, currentText.length() - 1) + operator;
            txt.setText(newText);
        } else {
            if (currentText.contains("+") || currentText.contains("-") || currentText.contains("×") || currentText.contains("÷")) {
                String result = calculator.calculate(currentText);

                if (result.contains("Error")) {
                    txt.setText(result);
                    return;
                }
                txt.setText(result + operator);
            } else {
                txt.setText(currentText + operator);
            }
        }
    }

    public void handleClearAll(ActionEvent event) throws IOException {
        txt.setText("0");
    }

    public void handleClearEntry(ActionEvent event) {
        String text = txt.getText();
        int lastOperatorIndex = -1;

        for (int i = text.length() - 1; i >= 0; i--) {
            if (calculator.getOperators().contains(text.charAt(i))) {
                lastOperatorIndex = i;
                break;
            }
        }
        if (lastOperatorIndex == -1) {
            txt.setText("0");
        } else if (lastOperatorIndex == text.length() - 1) {
        } else {
            String newText = text.substring(0, lastOperatorIndex + 1);
            txt.setText(newText);
        }
    }

    public void handleBackspace(ActionEvent event) {
        String currentText = txt.getText();

        if (currentText.length() <= 1) {
            txt.setText("0");
            return;
        }

        String newText = currentText.substring(0, currentText.length() - 1);
        txt.setText(newText);
    }

    public void handleDecimalPoint(ActionEvent event) {
        String currentText = txt.getText();

        if (isLastCharOperator(currentText)) {
            txt.setText(currentText + "0.");
            return;
        }

        int lastOpIndex = -1;
        for (int i = currentText.length() - 1; i >= 0; i--) {
            if (calculator.getOperators().contains(currentText.charAt(i))) {
                lastOpIndex = i;
                break;
            }
        }

        String lastNumber = currentText.substring(lastOpIndex + 1);

        if (!lastNumber.contains(",")) {
            txt.setText(currentText + ",");
        }
    }

    public void handleEquals(ActionEvent event) throws IOException {
        String currentText = txt.getText();
        String result = calculator.calculate(currentText);
        txt.setText(result);
    }

    public void handleToggleSign(ActionEvent event) throws IOException {
        String currentText = txt.getText();
        if (currentText.isEmpty() || currentText.equals("0")) return;

        try {
            double val = Double.parseDouble(currentText.replace(',', '.'));
            if (val == (long) val) {
                txt.setText(String.valueOf((long) -val));
            } else {
                txt.setText(String.valueOf(-val));
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
        if (text.length() == 1) {
            return false;
        }
        char lastChar = text.charAt(text.length() - 1);
        return calculator.getOperators().contains(lastChar);
    }
}
