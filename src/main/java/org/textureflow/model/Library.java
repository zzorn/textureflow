package org.textureflow.model;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;

/**
 * Library of possible effects.
 */
public class Library {

    // TODO: Sublibraries/folders etc

    private List<Effect> entries = new ArrayList<Effect>();

    public List<Effect> getEntries() {
        return entries;
    }

    public void addEntry(Effect entry) {
        entries.add(entry);
    }
}
