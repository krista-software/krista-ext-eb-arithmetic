package app.krista.extensions.ebplayground.ebtesting.ebarithmetic.service.validation;

// Tier 1: static primitive toolkit (non-instantiable). Every failure throws IllegalArgumentException
// with FIELD + WHY so the catalog request can turn it straight into an actionable failure response.
final class ValidationUtil {

    private ValidationUtil() {
    }

    static void requireNonNull(Double value, String field) {
        if (value == null) {
            throw new IllegalArgumentException(field + " is required. Please provide a numeric value for " + field + ".");
        }
    }
}
