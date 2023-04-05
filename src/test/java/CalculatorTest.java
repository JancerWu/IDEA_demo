import org.junit.Assert;
import org.junit.Test;


public class CalculatorTest {
    @Test
    public void shouldReturn2When1Plus1() {
        Calculator calculator = new Calculator();
        int actualResult = calculator.compute(1, 1, '+');
        int expectResult = 2;
        Assert.assertEquals(expectResult, actualResult);
    }

    @Test
    public void shouldReturn0When1Sub1() {
        Calculator calculator = new Calculator();
        int actualResult = calculator.compute(1, 1, '-');
        int expectResult = 0;
        Assert.assertEquals(expectResult, actualResult);
    }



}