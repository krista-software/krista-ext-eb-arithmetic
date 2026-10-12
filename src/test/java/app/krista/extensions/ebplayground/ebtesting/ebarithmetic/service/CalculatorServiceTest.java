package app.krista.extensions.ebplayground.ebtesting.ebarithmetic.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorServiceTest {

    private final CalculatorService calculatorService = new CalculatorService();

    @Test
    void add_returnsSumOfTwoPositiveNumbers() {
        assertEquals(5.0, calculatorService.add(2, 3));
    }

    @Test
    void add_returnsSumWithNegativeNumbers() {
        assertEquals(-1.0, calculatorService.add(-4, 3));
    }

    @Test
    void add_returnsSumWithDecimalNumbers() {
        assertEquals(3.3, calculatorService.add(1.1, 2.2), 0.0001);
    }

    @Test
    void subtract_returnsDifferenceOfTwoPositiveNumbers() {
        assertEquals(2.0, calculatorService.subtract(5, 3));
    }

    @Test
    void subtract_returnsDifferenceWithNegativeNumbers() {
        assertEquals(-9.0, calculatorService.subtract(-4, 5));
    }

    @Test
    void subtract_returnsNegativeResultWhenSecondIsLarger() {
        assertEquals(-5.0, calculatorService.subtract(3, 8));
    }

    @Test
    void multiply_returnsProductOfTwoPositiveNumbers() {
        assertEquals(12.0, calculatorService.multiply(4, 3));
    }

    @Test
    void multiply_returnsZeroWhenEitherOperandIsZero() {
        assertEquals(0.0, calculatorService.multiply(0, 42));
    }

    @Test
    void multiply_returnsProductWithNegativeNumbers() {
        assertEquals(-20.0, calculatorService.multiply(-4, 5));
    }

    @Test
    void divide_returnsQuotientOfTwoPositiveNumbers() {
        assertEquals(4.0, calculatorService.divide(8, 2));
    }

    @Test
    void divide_returnsDecimalResultWhenNotEvenlyDivisible() {
        assertEquals(2.5, calculatorService.divide(5, 2));
    }
}
