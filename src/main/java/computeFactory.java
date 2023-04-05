public class calculatorFactory {

    private static final Add add = new Add();
    private static final Sub sub = new Sub();

    public static Computable getComputable(char symbol) {
        return switch (symbol) {
            case '+' -> add;
            case '-' -> sub;
            default -> throw new IllegalArgumentException();
        };

    }
}
