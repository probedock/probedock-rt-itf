package io.probedock.rt.client.itf;

import io.probedock.client.annotations.ProbeTest;
import io.probedock.client.annotations.ProbeTestClass;
import io.probedock.jee.itf.model.Description;
import io.probedock.rt.client.Configuration;
import io.probedock.rt.client.Listener;
import io.probedock.itf.AbstractItfListener;

/**
 * Listener to send results to RT Agent. This listener is a wrapping of the {@link
 * io.probedock.rt.client.Listener}
 *
 * @author Laurent Prevost <laurent.prevost@probedock.io>
 */
public class ProbeDockRTListener extends AbstractItfListener {
    /**
     * RT configuration
     */
    private static final Configuration rtConfiguration = Configuration.getInstance();

    /**
     * wrapped listener
     */
    private Listener wrappedListener = new Listener();

    public ProbeDockRTListener() {
        super("Probe Dock RT ITF Listener");
    }

    @Override
    public void testRunStart() {
        super.testRunStart();

        if (!rtConfiguration.isEnabled()) {
            return;
        }

        wrappedListener.testRunStart(
            configuration.getProjectApiId(),
            configuration.getProjectVersion(),
            getCategory()
        );
    }

    @Override
    public void testRunEnd() {
        super.testRunEnd();

        if (!rtConfiguration.isEnabled()) {
            return;
        }

        wrappedListener.testRunEnd(
            configuration.getProjectApiId(),
            configuration.getProjectVersion(),
            getCategory(),
            endDate - startDate
        );
    }

    @Override
    public void testEnd(Description description) {
        super.testEnd(description);

        if (!rtConfiguration.isEnabled()) {
            return;
        }

        ProbeTest methodAnnotation = getMethodAnnotation(description);
        ProbeTestClass classANnotation = getClassAnnotation(description);

        wrappedListener.testResult(
            createTestResult(getFingerprint(description), description, methodAnnotation, classANnotation),
            configuration.getProjectApiId(),
            configuration.getProjectVersion(),
            getCategory()
        );
    }
}