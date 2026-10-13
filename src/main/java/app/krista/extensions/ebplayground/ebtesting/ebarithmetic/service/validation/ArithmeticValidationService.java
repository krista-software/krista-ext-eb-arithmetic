package app.krista.extensions.ebplayground.ebtesting.ebarithmetic.service.validation;

import org.jvnet.hk2.annotations.Service;

/**
 * Tier 2: domain validation service for the Calculator requests. Composes {@link ValidationUtil}
 * primitives; parses nothing (inputs are already typed Doubles) but enforces presence.
 */
@Service
public class ArithmeticValidationService {

    public void validateOperands(Double number1, Double number2) {
        ValidationUtil.requireNonNull(number1, "Number1");
        ValidationUtil.requireNonNull(number2, "Number2");
    }
}
