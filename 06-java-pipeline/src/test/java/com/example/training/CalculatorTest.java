package com.example.training;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Calculator Tests")
class CalculatorTest {

    private final Calculator calc = new Calculator();

    @Test
    @DisplayName("Addition should return the correct sum")
    void testAdd() {
        assertEquals(5, calc.add(2, 3));
        assertEquals(0, calc.add(-1, 1));
        assertEquals(-4, calc.add(-2, -2));
    }

    @Test
    @DisplayName("Subtraction should return the correct difference")
    void testSubtract() {
        assertEquals(1, calc.subtract(3, 2));
        assertEquals(-3, calc.subtract(0, 3));
    }

    @Test
    @DisplayName("Division by zero should throw IllegalArgumentException")
    void testDivideByZero() {
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> calc.divide(10, 0)
        );
        assertEquals("Division by zero is not allowed.", ex.getMessage());
    }

    @Test
    @DisplayName("Multiplication should return the correct product")
    void testMultiply() {
        assertEquals(12, calc.multiply(3, 4));
        assertEquals(0, calc.multiply(5, 0));
    }
}
