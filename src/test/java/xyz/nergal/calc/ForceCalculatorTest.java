package xyz.nergal.calc;

public class ForceCalculatorTest extends CalculatorTest {
    @Override
    protected Calculator getCalculator() {
        return new ForceCalculator();
    }
}
