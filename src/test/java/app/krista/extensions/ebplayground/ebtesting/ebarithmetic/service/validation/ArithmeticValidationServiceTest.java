package app.krista.extensions.ebplayground.ebtesting.ebarithmetic.service.validation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArithmeticValidationServiceTest {

    private final ArithmeticValidationService validationService = new ArithmeticValidationService();

    @Test
    void acceptsTwoValidNumbers() {
        assertDoesNotThrow(() -> validationService.validateOperands(1.0, 2.0));
    }

    @Test
    void rejectsMissingNumber1() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> validationService.validateOperands(null, 2.0));
        assertTrue(ex.getMessage().contains("Number1"));
    }

    @Test
    void rejectsMissingNumber2() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> validationService.validateOperands(1.0, null));
        assertTrue(ex.getMessage().contains("Number2"));
    }

    @Test
    void rejectsBothMissing() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> validationService.validateOperands(null, null));
        assertTrue(ex.getMessage().contains("Number1"));
    }
}
