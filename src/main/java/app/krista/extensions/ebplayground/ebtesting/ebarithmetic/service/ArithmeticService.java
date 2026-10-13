package app.krista.extensions.ebplayground.ebtesting.ebarithmetic.service;

import org.jvnet.hk2.annotations.Service;

/**
 * Pure arithmetic — framework-free so it is directly unit-testable (see the testing skill's
 * "pure Java only" policy). No HK2/Krista types appear in its API.
 */
@Service
public class ArithmeticService {

    public double add(double number1, double number2) {
        return number1 + number2;
    }

    public double subtract(double number1, double number2) {
        return number1 - number2;
    }
}
