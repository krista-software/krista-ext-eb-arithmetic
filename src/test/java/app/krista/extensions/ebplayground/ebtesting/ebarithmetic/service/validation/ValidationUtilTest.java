package app.krista.extensions.ebplayground.ebtesting.ebarithmetic.service.validation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidationUtilTest {

    @Test
    void requireNonNullPassesForNonNullValue() {
        assertDoesNotThrow(() -> ValidationUtil.requireNonNull(5.0, "Field"));
    }

    @Test
    void requireNonNullThrowsForNullValue() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> ValidationUtil.requireNonNull(null, "Field"));
        assertTrue(ex.getMessage().contains("Field"));
    }
}
