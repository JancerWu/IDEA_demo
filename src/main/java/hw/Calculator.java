package hw;

public class Calculator {
    public int compute(int num1, int num2, String symbol) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Computable computable = computeFactory.getComputable(symbol);
        return computable.compute(num1, num2);
    }

}
