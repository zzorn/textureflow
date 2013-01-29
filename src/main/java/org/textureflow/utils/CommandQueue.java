package org.textureflow.utils;

import org.textureflow.model.Project;

import java.util.*;

import static org.textureflow.utils.ParameterChecker.*;

/**
 *
 */
public class CommandQueue {

    private Project project;

    private final Deque<Command> undoQueue = new LinkedList<Command>();
    private final Deque<Command> redoQueue = new LinkedList<Command>();

    public CommandQueue() {
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
        undoQueue.clear();
        redoQueue.clear();
    }

    /**
     * Executes the specified command, and adds it to the undo queue if it should be added.
     * @param command command to execute
     */
    public void doCommand(Command command) {
        if (project != null) {
            checkNotNull(command, "command");

            final boolean executedOk = command.execute(project);

            if (executedOk) {
                if (command.isUndoable()) {
                    undoQueue.push(command);
                    redoQueue.clear();
                }

                if (command.isUndoHistoryClearing()) {
                    undoQueue.clear();
                    redoQueue.clear();
                }
            }
        }
    }

    /**
     * Undoes the last done command.
     */
    public void undo() {
        if (project != null && !undoQueue.isEmpty()) {
            final Command commandToUndo = undoQueue.pop();
            commandToUndo.undo(project);
            redoQueue.push(commandToUndo);
        }
    }

    /**
     * Redoes the last undone command.
     */
    public void redo() {
        if (project != null && !redoQueue.isEmpty()) {
            final Command commandToRedo = redoQueue.pop();
            commandToRedo.execute(project);
            undoQueue.push(commandToRedo);
        }
    }


}
