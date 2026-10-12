package app.krista.extensions.ebplayground.ebtesting.ebarithmetic.service.validation;

import org.jvnet.hk2.annotations.Service;

/**
 * Two-tier input validation for the Calculator requests. Every failure throws
 * IllegalArgumentException with FIELD + WHY + EXAMPLE — the signal the Area's audited wrapper
 * catches to produce an INPUT-classified failure response, per the business-logic skill's idiom.
 */
@Service
public class CalculationValidationService {

    public void validateOperands(Double firstNumber, Double secondNumber) {
        requireNonNull(firstNumber, "First Number");
        requireNonNull(secondNumber, "Second Number");
        requireFinite(firstNumber, "First Number");
        requireFinite(secondNumber, "Second Number");
    }

    public void validateDivisor(Double secondNumber) {
        if (secondNumber != null && secondNumber == 0.0) {
            throw new IllegalArgumentException(
                    "Second Number must not be zero. Division by zero is undefined. "
                            + "Provide a non-zero divisor, e.g. 2.");
        }
    }

    private void requireNonNull(Double value, String field) {
        if (value == null) {
            throw new IllegalArgumentException(
                    field + " is required. Please provide a numeric value for " + field + ".");
        }
    }

    private void requireFinite(Double value, String field) {
        if (value.isNaN() || value.isInfinite()) {
            throw new IllegalArgumentException(
                    field + " must be a finite number. Provide a valid numeric value, e.g. 42 or 3.14.");
        }
    }
}
