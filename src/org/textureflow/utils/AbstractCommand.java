package org.textureflow.utils;

import org.textureflow.model.Project;

/**
 * Some common functionality for commands.
 */
public abstract class AbstractCommand implements Command {

    private String description;
    private final boolean undoable;
    private final boolean undoHistoryClearing;

    public AbstractCommand() {
        this("Unknown Command", true, false);
    }

    public AbstractCommand(String description) {
        this(description, true, false);
    }

    public AbstractCommand(String description, boolean undoable, boolean undoHistoryClearing) {
        this.description = description;
        this.undoable = undoable;
        this.undoHistoryClearing = undoHistoryClearing;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void undo(Project project) {
        // Override if needed
    }

    @Override
    public boolean isUndoable() {
        return undoable;
    }

    @Override
    public boolean isUndoHistoryClearing() {
        return undoHistoryClearing;
    }
}
