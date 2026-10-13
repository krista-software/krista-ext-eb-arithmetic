package app.krista.extensions.ebplayground.ebtesting.ebarithmetic.catalog;

import app.krista.extension.executor.ExtensionResponse;
import app.krista.extension.executor.ExtensionResponseBuilder;
import app.krista.extension.impl.anno.Attribute;
import app.krista.extension.impl.anno.CatalogRequest;
import app.krista.extension.impl.anno.Domain;
import app.krista.extension.impl.anno.Field;
import app.krista.extensions.ebplayground.ebtesting.ebarithmetic.service.ArithmeticService;
import app.krista.extensions.ebplayground.ebtesting.ebarithmetic.service.validation.ArithmeticValidationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Calculator catalog requests — addition and subtraction of two numbers.
 *
 * Both methods are thin delegators per the testing policy: input validation lives in
 * {@link ArithmeticValidationService}, the arithmetic itself in {@link ArithmeticService} — both
 * pure Java classes covered by unit tests. Neither request calls an external system.
 */
@Domain(id = "catEntryDomain_738dcd68-fbd8-4207-a7b2-bac849a4362b",
        name = "EB Testing",
        ecosystemId = "catEntryEcosystem_e1557d10-6587-409e-aa1c-b1bbb4c42b17",
        ecosystemName = "EB Playground",
        ecosystemVersion = "d3046464-5295-4fb4-9b0c-78fb9c3f6c39")
public class CalculatorArea {

    private static final Logger LOG = LoggerFactory.getLogger(CalculatorArea.class);

    private final ArithmeticService arithmeticService;
    private final ArithmeticValidationService validationService;

    @Inject
    public CalculatorArea(ArithmeticService arithmeticService, ArithmeticValidationService validationService) {
        this.arithmeticService = arithmeticService;
        this.validationService = validationService;
    }

    @CatalogRequest(
            id = "localDomainRequest_a1e4c2d0-2222-4a2b-9c3d-000000000003",
            name = "Add Two Numbers",
            description = "Adds Number1 and Number2 and returns their sum.",
            area = "Calculator",
            type = CatalogRequest.Type.QUERY_SYSTEM)
    @Field(name = "Result", type = "Number", required = false,
            attributes = {@Attribute(name = "visualWidth", value = "S")}, options = {})
    public ExtensionResponse addTwoNumbers(
            @Field(name = "Number1", type = "Number", required = true,
                    attributes = {@Attribute(name = "visualWidth", value = "S")}, options = {}) Double number1,
            @Field(name = "Number2", type = "Number", required = true,
                    attributes = {@Attribute(name = "visualWidth", value = "S")}, options = {}) Double number2) {
        try {
            validationService.validateOperands(number1, number2);
            double result = arithmeticService.add(number1, number2);
            Map<String, Object> out = new LinkedHashMap<>();
            out.put("Result", result);
            return new ExtensionResponseBuilder().success(out).build();
        } catch (IllegalArgumentException e) {
            LOG.warn("Add Two Numbers rejected invalid input: {}", e.getMessage());
            return new ExtensionResponseBuilder().failure(e.getMessage()).build();
        } catch (Exception e) {
            LOG.error("Add Two Numbers failed unexpectedly", e);
            return new ExtensionResponseBuilder().failure("Unable to compute the sum: " + e.getMessage()).build();
        }
    }

    @CatalogRequest(
            id = "localDomainRequest_a1e4c2d0-2222-4a2b-9c3d-000000000004",
            name = "Subtract Two Numbers",
            description = "Subtracts Number2 from Number1 and returns the difference (Number1 - Number2).",
            area = "Calculator",
            type = CatalogRequest.Type.QUERY_SYSTEM)
    @Field(name = "Result", type = "Number", required = false,
            attributes = {@Attribute(name = "visualWidth", value = "S")}, options = {})
    public ExtensionResponse subtractTwoNumbers(
            @Field(name = "Number1", type = "Number", required = true,
                    attributes = {@Attribute(name = "visualWidth", value = "S")}, options = {}) Double number1,
            @Field(name = "Number2", type = "Number", required = true,
                    attributes = {@Attribute(name = "visualWidth", value = "S")}, options = {}) Double number2) {
        try {
            validationService.validateOperands(number1, number2);
            double result = arithmeticService.subtract(number1, number2);
            Map<String, Object> out = new LinkedHashMap<>();
            out.put("Result", result);
            return new ExtensionResponseBuilder().success(out).build();
        } catch (IllegalArgumentException e) {
            LOG.warn("Subtract Two Numbers rejected invalid input: {}", e.getMessage());
            return new ExtensionResponseBuilder().failure(e.getMessage()).build();
        } catch (Exception e) {
            LOG.error("Subtract Two Numbers failed unexpectedly", e);
            return new ExtensionResponseBuilder().failure("Unable to compute the difference: " + e.getMessage()).build();
        }
    }
}
