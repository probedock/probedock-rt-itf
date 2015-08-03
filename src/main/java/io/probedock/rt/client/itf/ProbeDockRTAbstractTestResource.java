package io.probedock.rt.client.itf;

import io.probedock.client.core.filters.FilterDefinition;
import io.probedock.itf.ProbeDockAbstractTestResource;
import io.probedock.jee.itf.listeners.Listener;
import io.probedock.rt.client.Configuration;
import io.probedock.rt.client.Filter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Add the RT capabilities to the standard resource
 *
 * @author Laurent Prévost <laurent.prevost@probedock.io>
 */
public abstract class ProbeDockRTAbstractTestResource extends ProbeDockAbstractTestResource {
    @Override
    public List<FilterDefinition> getAdditionalFilters() {
        return new Filter().getFilters();
    }

    @Override
    public Map<String, Listener> getAdditionalListeners() {
        if (Configuration.getInstance().isEnabled()) {
            Map<String, Listener> listeners = new HashMap<>();
            listeners.put("rtListener", new ProbeDockRTListener());
            return listeners;
        } else {
            return new HashMap<>();
        }
    }
}
