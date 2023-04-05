public class Calculator {
    public int compute(int number1, int number2, char symbol) {
        return switch (symbol) {
            case '+' -> number1 + number2;
            case '-' -> number1 - number2;
            default -> throw new IllegalArgumentException();
        };
    }

}
