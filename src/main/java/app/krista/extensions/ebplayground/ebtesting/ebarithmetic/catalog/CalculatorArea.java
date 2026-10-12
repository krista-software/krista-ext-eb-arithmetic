package app.krista.extensions.ebplayground.ebtesting.ebarithmetic.catalog;

import app.krista.extension.executor.ExtensionResponse;
import app.krista.extension.executor.ExtensionResponseBuilder;
import app.krista.extension.impl.anno.Attribute;
import app.krista.extension.impl.anno.CatalogRequest;
import app.krista.extension.impl.anno.Domain;
import app.krista.extension.impl.anno.Field;
import app.krista.extensions.ebplayground.ebtesting.ebarithmetic.service.CalculatorService;
import app.krista.extensions.ebplayground.ebtesting.ebarithmetic.service.validation.CalculationValidationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Calculator catalog requests — addition, subtraction, multiplication, division of two numbers.
 *
 * Every method is a thin delegator: resolve inputs, validate, call CalculatorService, build the
 * response. All real logic lives in the pure, unit-tested CalculatorService / CalculationValidationService
 * classes (per the testing skill's policy) — there is no external system involved anywhere here.
 */
@Domain(id = "catEntryDomain_738dcd68-fbd8-4207-a7b2-bac849a4362b",
        name = "EB Testing",
        ecosystemId = "catEntryEcosystem_e1557d10-6587-409e-aa1c-b1bbb4c42b17",
        ecosystemName = "EB Playground",
        ecosystemVersion = "f4e1101d-5d3d-4194-9b6d-64331ecd5bd2")
public class CalculatorArea {

    private static final Logger LOG = LoggerFactory.getLogger(CalculatorArea.class);

    private final CalculatorService calculatorService;
    private final CalculationValidationService validationService;

    @Inject
    public CalculatorArea(CalculatorService calculatorService, CalculationValidationService validationService) {
        this.calculatorService = calculatorService;
        this.validationService = validationService;
    }

    @CatalogRequest(
            id = "localDomainRequest_a1b2c3d4-0001-4eba-9c01-000000000001",
            name = "Add",
            description = "Add two numbers together and return the sum.",
            area = "Calculator",
            type = CatalogRequest.Type.QUERY_SYSTEM)
    @Field(name = "Result", type = "Number", required = false, attributes = {}, options = {})
    public ExtensionResponse add(
            @Field(name = "First Number", type = "Number", required = true,
                    attributes = {@Attribute(name = "visualWidth", value = "S")}, options = {}) Double firstNumber,
            @Field(name = "Second Number", type = "Number", required = true,
                    attributes = {@Attribute(name = "visualWidth", value = "S")}, options = {}) Double secondNumber) {
        return audited("Add", () -> {
            validationService.validateOperands(firstNumber, secondNumber);
            double result = calculatorService.add(firstNumber, secondNumber);
            return resultResponse(result);
        });
    }

    @CatalogRequest(
            id = "localDomainRequest_a1b2c3d4-0002-4eba-9c01-000000000002",
            name = "Subtract",
            description = "Subtract the second number from the first and return the difference.",
            area = "Calculator",
            type = CatalogRequest.Type.QUERY_SYSTEM)
    @Field(name = "Result", type = "Number", required = false, attributes = {}, options = {})
    public ExtensionResponse subtract(
            @Field(name = "First Number", type = "Number", required = true,
                    attributes = {@Attribute(name = "visualWidth", value = "S")}, options = {}) Double firstNumber,
            @Field(name = "Second Number", type = "Number", required = true,
                    attributes = {@Attribute(name = "visualWidth", value = "S")}, options = {}) Double secondNumber) {
        return audited("Subtract", () -> {
            validationService.validateOperands(firstNumber, secondNumber);
            double result = calculatorService.subtract(firstNumber, secondNumber);
            return resultResponse(result);
        });
    }

    @CatalogRequest(
            id = "localDomainRequest_a1b2c3d4-0003-4eba-9c01-000000000003",
            name = "Multiply",
            description = "Multiply two numbers together and return the product.",
            area = "Calculator",
            type = CatalogRequest.Type.QUERY_SYSTEM)
    @Field(name = "Result", type = "Number", required = false, attributes = {}, options = {})
    public ExtensionResponse multiply(
            @Field(name = "First Number", type = "Number", required = true,
                    attributes = {@Attribute(name = "visualWidth", value = "S")}, options = {}) Double firstNumber,
            @Field(name = "Second Number", type = "Number", required = true,
                    attributes = {@Attribute(name = "visualWidth", value = "S")}, options = {}) Double secondNumber) {
        return audited("Multiply", () -> {
            validationService.validateOperands(firstNumber, secondNumber);
            double result = calculatorService.multiply(firstNumber, secondNumber);
            return resultResponse(result);
        });
    }

    @CatalogRequest(
            id = "localDomainRequest_a1b2c3d4-0004-4eba-9c01-000000000004",
            name = "Divide",
            description = "Divide the first number by the second and return the quotient.",
            area = "Calculator",
            type = CatalogRequest.Type.QUERY_SYSTEM)
    @Field(name = "Result", type = "Number", required = false, attributes = {}, options = {})
    public ExtensionResponse divide(
            @Field(name = "First Number", type = "Number", required = true,
                    attributes = {@Attribute(name = "visualWidth", value = "S")}, options = {}) Double firstNumber,
            @Field(name = "Second Number", type = "Number", required = true,
                    attributes = {@Attribute(name = "visualWidth", value = "S")}, options = {}) Double secondNumber) {
        return audited("Divide", () -> {
            validationService.validateOperands(firstNumber, secondNumber);
            validationService.validateDivisor(secondNumber);
            double result = calculatorService.divide(firstNumber, secondNumber);
            return resultResponse(result);
        });
    }

    private ExtensionResponse resultResponse(double result) {
        Map<String, Object> out = new LinkedHashMap<>();
        out.put("Result", result);
        return new ExtensionResponseBuilder().success(out).build();
    }

    /** Uniform try/catch -> classified ExtensionResponse, per the business-logic skill's "audited" idiom. */
    private ExtensionResponse audited(String name, Supplier<ExtensionResponse> action) {
        try {
            return action.get();
        } catch (IllegalArgumentException e) {
            LOG.warn("Catalog '{}' rejected invalid input: {}", name, e.getMessage());
            return new ExtensionResponseBuilder().failure(e.getMessage()).build();
        } catch (Exception e) {
            LOG.error("Catalog '{}' failed unexpectedly", name, e);
            return new ExtensionResponseBuilder()
                    .failure("An unexpected error occurred while computing the result.").build();
        }
    }
}
