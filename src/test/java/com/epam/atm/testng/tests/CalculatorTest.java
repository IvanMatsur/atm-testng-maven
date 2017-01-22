package com.epam.atm.testng.tests;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.epam.atm.testng.utilities.Timeout;
import com.epam.tat.module4.Calculator;

/**
 * Created by Ivan_Matsur on 1/19/2017.
 */
public class CalculatorTest {
  Calculator calculator = new Calculator();
  DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

  @BeforeMethod(alwaysRun = true)
  public void beforeMethod() {
    System.out.println("Test started running at: " + dateFormat.format(System.currentTimeMillis()));
    System.out.println("Current Thread Id is: " + Thread.currentThread().getId());
    Timeout.waitFor(500);
  }

  @AfterMethod(alwaysRun = true)
  public void afterMethod() {
    Timeout.waitFor(500);
  }

  @Test(dataProvider = "dataToTestSum", description = "testing sum method", groups = "positive")
  public void testSum(double a, double b, double expected) {
    double res = calculator.sum(a, b);
    Assert.assertEquals(res, expected);
  }

  @DataProvider(name = "dataToTestSum")
  public Object[][] dataToTestSum() {
    return new Object[][] {
        { 5.0, 3.0, 8.0 },
        { 1.6, 2.2, 3.8 },
        { 0.234, 3.467, 3.701 },
        { -0.34, -0.75, -1.09 },
        { -0.89, 1.65, 0.76 },
        { 4.83, -15.21, -10.38 },
        { 0, 3.48, 3.48 },
        { 0, -234.9, -234.9 },
        { 67.86, 0, 67.86 },
        { -45.37, 0, -45.37 } };
  }

  @Test(description = "result of division by 0 throw Exception",
      expectedExceptions = NumberFormatException.class,
      expectedExceptionsMessageRegExp = "Attempt to divide by zero",
      groups = "positive")
  public void testDivisionByZero() {
    calculator.div(1, 0);
  }

  @Test(description = "sum of 0 and 3.14 is equal to 3.14", groups = "positive")
  public void testSumWithZero() {
    double res = calculator.sum(0.0, 3.14);
    Assert.assertEquals(res, 3.14);
  }

  @Test(description = "result of subtraction 11 from 13 is equal to 2", groups = "positive")
  public void testSubtraction() {
    double res = calculator.sub(13.0, 11.0);
    Assert.assertEquals(res, 2.0);
  }

  @Test(description = "result of subtraction 0 from 2.22 is equal to 2.22", groups = "positive")
  public void testSubtractionWithZero() {
    double res = calculator.sub(2.22, 0.0);
    Assert.assertEquals(res, 2.22);
  }

  @Test(description = "result of division 14 by 5 is equal to 2.8", groups = "positive")
  public void testDivision() {
    double res = calculator.div(14.0, 5.0);
    Assert.assertEquals(res, 2.8);
  }

  @Test(description = "result of division 0 by 18.56 is equal to 0", groups = "positive")
  public void testDivisionWithZero() {
    double res = calculator.div(0.0, 18.56);
    Assert.assertEquals(res, 0.0);
  }

  @Test(description = "result of multiply 0.21 by 8 is equal to 1.68", groups = "positive")
  public void testMultiply() {
    double res = calculator.mult(0.21, 8.0);
    Assert.assertEquals(res, 1.68);
  }

  @Test(description = "result of multiply 0 by 12.56 is equal to 0.0", groups = "positive")
  public void testMultiplyWithZero() {
    double res = calculator.mult(0.0, 12.56);
    Assert.assertEquals(res, 0.0);
  }

  @Test(description = "result of multiply 9.99 by 0 is equal to 0.0", groups = "positive")
  public void testMultiplyByZero() {
    double res = calculator.mult(9.99, 0);
    Assert.assertEquals(res, 0.0);
  }

  @Test(description = "negative value is negative", groups = "positive")
  public void testIsNegative() {
    long value = -10;
    boolean res = calculator.isNegative(value);
    Assert.assertTrue(res);
  }

  @Test(description = "positive value is positive", groups = "positive")
  public void testIsPositive() {
    long value = 10;
    boolean res = calculator.isPositive(value);
    Assert.assertTrue(res);
  }

  @Test(description = "sqrt of 4 is equal to 16", groups = "positive")
  public void testSQRT() {
    double res = calculator.sqrt(4);
    Assert.assertEquals(res, 2.0);
  }

  @Test(description = "sqrt of 0 is equal to 0", groups = "positive")
  public void testSQRTZero() {
    double res = calculator.sqrt(0);
    Assert.assertEquals(res, 0.0);
  }

  @Test(description = "pow of 2 in 4 tier is equal to 16", groups = "positive")
  public void testPOW() {
    double res = calculator.pow(2.0, 4.0);
    Assert.assertEquals(res, 16.0);
  }

  @Test(description = "pow of 2 in 0 tier is equal to 1", groups = "positive")
  public void testZeroPOW() {
    double res = calculator.pow(2.0, 0.0);
    Assert.assertEquals(res, 1.0);
  }

  @Test(description = "pow of 0 in 3 tier is equal to 1", groups = "positive")
  public void testZeroValuePOW() {
    double res = calculator.pow(0.0, 3.0);
    Assert.assertEquals(res, 0.0);
  }

  @Test(description = "pow of 0 in 0 tier is not equal to 0", dependsOnGroups = "positive", groups = "negative")
  public void testBothZeroPOW() {
    double res = calculator.pow(0.0, 0.0);
    Assert.assertNotEquals(res, 0.0);
  }

  @Test(description = "positive value is not negative", dependsOnGroups = "positive", groups = "negative")
  public void testIsNotNegative() {
    long value = 10;
    boolean res = calculator.isNegative(value);
    Assert.assertFalse(res);
  }

  @Test(description = "zero is not negative", dependsOnGroups = "positive", groups = "negative")
  public void testZeroIsNotNegative() {
    long value = 0;
    boolean res = calculator.isNegative(value);
    Assert.assertFalse(res);
  }

  @Test(description = "negative value is not positive", dependsOnGroups = "positive", groups = "negative")
  public void testIsNotPositive() {
    long value = -10;
    boolean res = calculator.isPositive(value);
    Assert.assertFalse(res);
  }

  @Test(description = "zero value is not positive", dependsOnGroups = "positive", groups = "negative")
  public void testZeroIsNotPositive() {
    long value = 0;
    boolean res = calculator.isPositive(value);
    Assert.assertFalse(res);
  }
}