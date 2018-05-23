package xyz.nergal.calc;

public class PolishCalculatorTest extends CalculatorTest {
    @Override
    protected Calculator getCalculator() {
        return new PolishCalculator();
    }
}
