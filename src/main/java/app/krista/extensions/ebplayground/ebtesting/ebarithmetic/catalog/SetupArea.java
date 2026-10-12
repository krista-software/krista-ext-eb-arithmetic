package app.krista.extensions.ebplayground.ebtesting.ebarithmetic.catalog;

import app.krista.extension.executor.ExtensionResponse;
import app.krista.extension.executor.ExtensionResponseBuilder;
import app.krista.extension.executor.Invoker;
import app.krista.extension.impl.anno.CatalogRequest;
import app.krista.extension.impl.anno.Domain;
import app.krista.extension.impl.anno.Field;
import app.krista.extensions.ebplayground.ebtesting.ebarithmetic.service.CalculatorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Setup area — the standard Test Connection + Health Check requests.
 *
 * This extension has no external system to connect to, so "Test Connection" instead proves the
 * extension's own computation logic is loaded and correct (a fixed self-check calculation) rather
 * than checking network/credential connectivity.
 */
@Domain(id = "catEntryDomain_738dcd68-fbd8-4207-a7b2-bac849a4362b",
        name = "EB Testing",
        ecosystemId = "catEntryEcosystem_e1557d10-6587-409e-aa1c-b1bbb4c42b17",
        ecosystemName = "EB Playground",
        ecosystemVersion = "f4e1101d-5d3d-4194-9b6d-64331ecd5bd2")
public class SetupArea {

    private static final Logger LOG = LoggerFactory.getLogger(SetupArea.class);

    private final CalculatorService calculatorService;
    private final String invokerId;

    @Inject
    public SetupArea(CalculatorService calculatorService, Invoker invoker) {
        this.calculatorService = calculatorService;
        this.invokerId = invoker.getInvokerId();
    }

    @CatalogRequest(
            id = "localDomainRequest_a1b2c3d4-0005-4eba-9c01-000000000005",
            name = "Test Connection",
            description = "Verify the extension's arithmetic logic is loaded and working correctly.",
            area = "Setup",
            type = CatalogRequest.Type.QUERY_SYSTEM,
            tool = true)
    @Field.Boolean(name = "Success", required = false)
    @Field.Text(name = "Message", required = false)
    public ExtensionResponse testConnection() {
        Map<String, Object> out = new LinkedHashMap<>();
        try {
            double selfCheck = calculatorService.add(2, 2);
            if (selfCheck != 4.0) {
                throw new IllegalStateException("Self-check failed: expected 2 + 2 = 4, got " + selfCheck);
            }
            out.put("Success", true);
            out.put("Message", "EB Arithmetic is loaded and its calculations are working correctly.");
        } catch (Exception e) {
            LOG.error("Test connection self-check failed for invoker {}", invokerId, e);
            out.put("Success", false);
            out.put("Message", e.getMessage());
        }
        return new ExtensionResponseBuilder().success(out).build();
    }

    @CatalogRequest(
            id = "localDomainRequest_a1b2c3d4-0006-4eba-9c01-000000000006",
            name = "Health Check",
            description = "Check extension and JVM health.",
            area = "Setup",
            type = CatalogRequest.Type.CHANGE_SYSTEM,
            tool = true)
    @Field.Boolean(name = "Is Healthy", required = false)
    @Field(name = "Heap Used MB", type = "Number", required = false, attributes = {}, options = {})
    @Field.Text(name = "Details", required = false)
    public ExtensionResponse healthCheck() {
        Map<String, Object> out = new LinkedHashMap<>();
        MemoryMXBean memBean = ManagementFactory.getMemoryMXBean();
        long heapUsedMb = memBean.getHeapMemoryUsage().getUsed() / (1024 * 1024);
        boolean healthy = calculatorService.add(2, 2) == 4.0;
        out.put("Is Healthy", healthy);
        out.put("Heap Used MB", (double) heapUsedMb);
        out.put("Details", healthy ? "OK" : "Arithmetic self-check failed.");
        return new ExtensionResponseBuilder().success(out).build();
    }
}
