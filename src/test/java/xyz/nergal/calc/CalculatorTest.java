package xyz.nergal.calc;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {
  private Calculator calculator;

  @Before
  public void createCalculator() {
    calculator = new Calculator();
  }

  @Test
  public void simpleSummation() {
    String calculation = "700 + 90";
    double expected = 700D + 90D;

    Assert.assertEquals(expected, calculator.calc(calculation), 2);
  }

  @Test
  public void complexSummation() {
    String calculation = "700 + 90 + 210 + 70";
    double expected = 700D + 90D + 210D + 70D;

    Assert.assertEquals(expected, calculator.calc(calculation), 2);
  }

  @Test
  public void simpleSubtraction() {
    String calculation = "700 - 90";
    double expected = 700D - 90D;

    Assert.assertEquals(expected, calculator.calc(calculation), 2);
  }

  @Test
  public void complexSubtraction() {
    String calculation = "700 - 90 - 210 - 70";
    double expected = 700D - 90D - 210D - 70D;

    Assert.assertEquals(expected, calculator.calc(calculation), 2);
  }

  @Test
  public void simpleMultiplication() {
    String calculation = "700 * 90";
    double expected = 700D * 90D;

    Assert.assertEquals(expected, calculator.calc(calculation), 2);
  }

  @Test
  public void complexMultiplication() {
    String calculation = "700 * 90 * 210 * 70";
    double expected = 700D * 90D * 210D * 70D;

    Assert.assertEquals(expected, calculator.calc(calculation), 2);
  }

  @Test
  public void simpleDivision() {
    String calculation = "700 / 90";
    double expected = 700D / 90D;

    Assert.assertEquals(expected, calculator.calc(calculation), 2);
  }

  @Test
  public void complexDivision() {
    String calculation = "700 / 90 / 210 / 70";
    double expected = 700D / 90D / 210D / 70D;

    Assert.assertEquals(expected, calculator.calc(calculation), 2);
  }

  @Test
  public void simpleCalculation() {
    String calculation = "5 + 14 / 2 * 10 - 20";
    double expected = 5D + 14D / 2D * 10D - 20D;

    Assert.assertEquals(expected, calculator.calc(calculation), 2);
  }

  @Test
  public void complexCalculation() {
    String calculation = "5 + 14 / 2 * (10 - 20) + 1";
    double expected = 5D + 14D / 2D * (10D - 20D) + 1D;

    Assert.assertEquals(expected, calculator.calc(calculation), 2);
  }

}
