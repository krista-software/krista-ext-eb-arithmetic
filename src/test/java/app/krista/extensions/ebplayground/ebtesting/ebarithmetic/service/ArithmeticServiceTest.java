package app.krista.extensions.ebplayground.ebtesting.ebarithmetic.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArithmeticServiceTest {

    private final ArithmeticService service = new ArithmeticService();

    @Test
    void addsTwoPositiveNumbers() {
        assertEquals(5.0, service.add(2.0, 3.0));
    }

    @Test
    void addsNegativeAndPositiveNumbers() {
        assertEquals(-1.0, service.add(-4.0, 3.0));
    }

    @Test
    void addsDecimalNumbers() {
        assertEquals(3.3, service.add(1.1, 2.2), 0.0001);
    }

    @Test
    void subtractsTwoPositiveNumbers() {
        assertEquals(7.0, service.subtract(10.0, 3.0));
    }

    @Test
    void subtractsResultingInNegativeNumber() {
        assertEquals(-5.0, service.subtract(10.0, 15.0));
    }

    @Test
    void subtractsDecimalNumbers() {
        assertEquals(1.1, service.subtract(3.3, 2.2), 0.0001);
    }

    @Test
    void subtractingZeroReturnsSameNumber() {
        assertEquals(42.0, service.subtract(42.0, 0.0));
    }
}
