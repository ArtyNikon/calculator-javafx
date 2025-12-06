package Calculator;

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
}
