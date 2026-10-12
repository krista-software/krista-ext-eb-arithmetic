package app.krista.extensions.ebplayground.ebtesting.ebarithmetic.service.validation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CalculationValidationServiceTest {

    private final CalculationValidationService validationService = new CalculationValidationService();

    @Test
    void validateOperands_acceptsTwoFiniteNumbers() {
        assertDoesNotThrow(() -> validationService.validateOperands(1.5, -2.5));
    }

    @Test
    void validateOperands_rejectsNullFirstNumber() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> validationService.validateOperands(null, 2.0));
        assertTrue(ex.getMessage().contains("First Number"));
    }

    @Test
    void validateOperands_rejectsNullSecondNumber() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> validationService.validateOperands(2.0, null));
        assertTrue(ex.getMessage().contains("Second Number"));
    }

    @Test
    void validateOperands_rejectsNonFiniteNumber() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> validationService.validateOperands(Double.NaN, 2.0));
        assertTrue(ex.getMessage().contains("finite"));
    }

    @Test
    void validateDivisor_acceptsNonZeroDivisor() {
        assertDoesNotThrow(() -> validationService.validateDivisor(2.0));
    }

    @Test
    void divide_rejectsDivisionByZeroWithValidationError() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> validationService.validateDivisor(0.0));
        assertTrue(ex.getMessage().contains("must not be zero"));
    }
}
