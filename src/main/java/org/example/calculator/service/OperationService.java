package org.example.calculator.service;

public class OperationService implements Operations {
    public static Double result;

    @Override
    public double addition(double a, double b) {
        result = a + b;
        return result;
    }

    @Override
    public double subtraction(double a, double b) {
        result = a - b;
        return result;
    }

    @Override
    public double multiplication(double a, double b) {
        result = a * b;
        return result;
    }

    @Override
    public double division(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Деление на ноль невозможно");
        }
        result = a / b;
        return result;
    }

    @Override
    public double reciprocal(double a) {
        if (a == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return 1.0 / a;
    }

    @Override
    public double square(double a) {
        return a * a;
    }

    @Override
    public double squareRoot(double a) {
        if (a < 0) {
            throw new ArithmeticException("Invalid input for square root");
        }
        return Math.sqrt(a);
    }

    @Override
    public double calculatePercentage(double a) {
        return a / 100.0;
    }
}
