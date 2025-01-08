package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringCalculatorTest {
    @Test
    public void emptyStringReturnsZero() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(0, calculator.add(""));
    }

    @Test
    void singleNumberReturnsTheNumber() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(1, calculator.add("1"));
    }

    @Test
    void twoNumbersReturnSum() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(3, calculator.add("1,2"));
    }

    @Test
    void numbersWithNewLinesReturnSum() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(6, calculator.add("1\n2,3"));
    }

    @Test
    void differentDelimiter() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(3, calculator.add("//;\n1;2"));
    }

    @Test
    void negativeNumberThrowsException() {
        StringCalculator calculator = new StringCalculator();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> calculator.add("-1"));
        assertEquals("negative numbers not allowed -1", exception.getMessage());
    }

    @Test
    void multipleNegativeNumbersThrowsException() {
        StringCalculator calculator = new StringCalculator();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> calculator.add("-1,-2,-3"));
        assertEquals("negative numbers not allowed -1, -2, -3", exception.getMessage());
    }

    @Test
    void numbersGreaterThan1000AreIgnored() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(2, calculator.add("1001,2"));
    }

    @Test
    void differentDelimiterWithMultipleCharacters() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(6, calculator.add("//[***]\n1***2***3"));
    }

    @Test
    void multipleDifferentDelimitersWithMultipleCharacters() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(6, calculator.add("//[*][%]\n1*2%3"));
    }

    @Test
    void numbersGreaterThan1000AreIgnoredWithMultipleDelimiters() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(3, calculator.add("//[*][%]\n1*1001%2"));
    }
}
