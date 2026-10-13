package app.krista.extensions.ebplayground.ebtesting.ebarithmetic.catalog;

import app.krista.extension.executor.ExtensionResponse;
import app.krista.extension.executor.ExtensionResponseBuilder;
import app.krista.extension.executor.Invoker;
import app.krista.extension.impl.anno.CatalogRequest;
import app.krista.extension.impl.anno.Domain;
import app.krista.extension.impl.anno.Field;

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
 * EB Arithmetic has no external system to reach, so Test Connection simply confirms the extension
 * is loaded; Health Check reports JVM heap usage the same way every extension's Setup area does.
 */
@Domain(id = "catEntryDomain_738dcd68-fbd8-4207-a7b2-bac849a4362b",
        name = "EB Testing",
        ecosystemId = "catEntryEcosystem_e1557d10-6587-409e-aa1c-b1bbb4c42b17",
        ecosystemName = "EB Playground",
        ecosystemVersion = "d3046464-5295-4fb4-9b0c-78fb9c3f6c39")
public class SetupArea {

    private static final Logger LOG = LoggerFactory.getLogger(SetupArea.class);

    private final String invokerId;

    @Inject
    public SetupArea(Invoker invoker) {
        this.invokerId = invoker.getInvokerId();
    }

    @CatalogRequest(
            id = "localDomainRequest_a1e4c2d0-1111-4a2b-9c3d-000000000001",
            name = "Test Connection",
            description = "Verify the extension is loaded and responding. EB Arithmetic performs local computation only, so there is no external system to connect to.",
            area = "Setup",
            type = CatalogRequest.Type.QUERY_SYSTEM)
    @Field.Boolean(name = "Success", required = false)
    @Field.Text(name = "Message", required = false)
    public ExtensionResponse testConnection() {
        LOG.info("Test Connection invoked for invoker {}", invokerId);
        Map<String, Object> out = new LinkedHashMap<>();
        out.put("Success", true);
        out.put("Message", "EB Arithmetic has no external dependencies; the extension is loaded and ready.");
        return new ExtensionResponseBuilder().success(out).build();
    }

    @CatalogRequest(
            id = "localDomainRequest_a1e4c2d0-1111-4a2b-9c3d-000000000002",
            name = "Health Check",
            description = "Report extension and JVM health.",
            area = "Setup",
            type = CatalogRequest.Type.CHANGE_SYSTEM)
    @Field.Boolean(name = "Is Healthy", required = false)
    @Field(name = "Heap Used MB", type = "Number", required = false, attributes = {}, options = {})
    @Field.Text(name = "Details", required = false)
    public ExtensionResponse healthCheck() {
        LOG.info("Health Check invoked for invoker {}", invokerId);
        MemoryMXBean memBean = ManagementFactory.getMemoryMXBean();
        long heapUsedMb = memBean.getHeapMemoryUsage().getUsed() / (1024 * 1024);
        Map<String, Object> out = new LinkedHashMap<>();
        out.put("Is Healthy", true);
        out.put("Heap Used MB", (double) heapUsedMb);
        out.put("Details", "OK");
        return new ExtensionResponseBuilder().success(out).build();
    }
}
