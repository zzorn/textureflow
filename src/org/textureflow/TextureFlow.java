package org.textureflow;

import org.textureflow.model.Project;
import org.textureflow.ui.MainUi;
import org.textureflow.utils.AbstractCommand;
import org.textureflow.utils.Command;
import org.textureflow.utils.CommandAction;
import org.textureflow.utils.CommandQueue;

import javax.swing.*;

/**
 *
 */
public class TextureFlow {

    private static Project currentProject = new Project();
    private static final CommandQueue commandQueue = new CommandQueue();
    private static MainUi mainUi;

    public static final CommandAction EXIT_ACTION = new CommandAction("Exit") {
        @Override
        protected Command createCommand() {
            return new AbstractCommand() {
                @Override
                public boolean execute(Project project) {
                    if (currentProject != null && currentProject.hasUnsavedChanges()) {
                        final int result = JOptionPane.showConfirmDialog(
                                mainUi.getFrame(),
                                "The project has unsaved changes, exit anyway?",
                                "Unsaved changes",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE);
                        if (result == 1) System.exit(0);
                    }
                    else {
                        System.exit(0);
                    }

                    return false;
                }
            };
        }
    };

    public static CommandQueue getCommandQueue() {
        return commandQueue;
    }

    public static Project getCurrentProject() {
        return currentProject;
    }

    public static void setCurrentProject(Project currentProject) {
        TextureFlow.currentProject = currentProject;
        commandQueue.setProject(currentProject);
    }

    public static void main(String[] args) {
        commandQueue.setProject(currentProject);

        mainUi = new MainUi();
        mainUi.startMainUi();
    }


}
