package app.krista.extensions.ebplayground.ebtesting.ebarithmetic;

import app.krista.extension.executor.Invoker;
import app.krista.extension.impl.anno.ChangeLog;
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
 * This extension performs all of its work locally (addition and subtraction of two numbers) and
 * never calls an external system, so it declares no connection @Field.* attributes and needs no
 * VALIDATE_ATTRIBUTES / TEST_CONNECTION / INVOKER_UPDATED hooks — there is no configuration to
 * validate, no connectivity to test, and no saved attributes to react to.
 */
@Extension(name = "EB Arithmetic", version = "1.0.0",
        description = "Performs basic addition and subtraction of two numbers. Self-contained; no external system or credentials required.")
@StaticResource(path = "docs", file = "docs")
@Java(version = Java.Version.JAVA_21)
@ChangeLog(file = "docs/pages/ReleaseNotes.md")
public final class EbArithmeticExtension {

    private static final Logger LOGGER = LoggerFactory.getLogger(EbArithmeticExtension.class);

    private final Invoker invoker;

    @Inject
    public EbArithmeticExtension(Invoker invoker) {
        this.invoker = invoker;
        LOGGER.info("EB Arithmetic extension loaded for invoker {}", invoker.getInvokerId());
    }

    /** Setup-tab tabs. Only the Documentation tab applies — there is no Authentication/Audit tab. */
    @InvokerRequest(InvokerRequest.Type.CUSTOM_TABS)
    public Map<String, String> customTabs() {
        return Map.of("Documentation", "static/docs");
    }
}
