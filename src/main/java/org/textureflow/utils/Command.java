package org.textureflow.utils;

import org.textureflow.model.Project;

/**
 * A single command that can be executed.
 */
public interface Command {

    String getDescription();
    void setDescription(String description);

    /**
     * Execute command
     * @return true if it should be saved in undo queue, false otherwise.
     */
    boolean execute(Project project);

    void undo(Project project);

    boolean isUndoable();
    boolean isUndoHistoryClearing();

}
