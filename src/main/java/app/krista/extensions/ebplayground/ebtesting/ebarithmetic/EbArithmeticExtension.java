package app.krista.extensions.ebplayground.ebtesting.ebarithmetic;

import app.krista.extension.executor.Invoker;
import app.krista.extension.impl.anno.Extension;
import app.krista.extension.impl.anno.InvokerRequest;
import app.krista.extension.impl.anno.Java;
import app.krista.extension.impl.anno.StaticResource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Map;

/**
 * EB Arithmetic extension entry point.
 *
 * This is a self-contained sample extension: every catalog request is computed locally inside the
 * extension, so there is no external system to authenticate against or configure — no connection
 * fields, no Setup/Authentication tab.
 */
@Extension(name = "EB Arithmetic", version = "1.0.0",
        description = "Performs basic arithmetic (addition, subtraction, multiplication, division) on two numbers.")
@StaticResource(path = "docs", file = "docs")
@Java(version = Java.Version.JAVA_21)
public final class EbArithmeticExtension {

    private static final Logger LOGGER = LoggerFactory.getLogger(EbArithmeticExtension.class);

    private final Invoker invoker;

    @Inject
    public EbArithmeticExtension(Invoker invoker) {
        this.invoker = invoker;
        LOGGER.info("EB Arithmetic extension loaded for invoker {}", invoker.getInvokerId());
    }

    /** Setup-tab tabs. No Authentication tab — this extension has no external credentials. */
    @InvokerRequest(InvokerRequest.Type.CUSTOM_TABS)
    public Map<String, String> customTabs() {
        return Map.of("Documentation", "static/docs");
    }
}
