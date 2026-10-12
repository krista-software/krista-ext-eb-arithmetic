package app.krista.extensions.ebplayground.ebtesting.ebarithmetic.service;

import org.jvnet.hk2.annotations.Service;

/**
 * Pure arithmetic — no external system, no I/O. Framework-free on purpose so it can be unit-tested
 * directly (new CalculatorService()), per the testing skill's "extract logic to a plain class" rule.
 */
@Service
public class CalculatorService {

    public double add(double first, double second) {
        return first + second;
    }

    public double subtract(double first, double second) {
        return first - second;
    }

    public double multiply(double first, double second) {
        return first * second;
    }

    /** Callers must reject a zero divisor before calling this (see CalculationValidationService). */
    public double divide(double first, double second) {
        return first / second;
    }
}
