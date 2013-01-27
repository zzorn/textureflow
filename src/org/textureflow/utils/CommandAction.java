package org.textureflow.utils;

import org.textureflow.TextureFlow;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Action that creates a command and puts it in the specified command queue.
 */
public abstract class CommandAction extends AbstractAction {

    private final String commandName;

    public CommandAction(String name) {
        super(name);
        commandName = name;
    }

    @Override
    public final void actionPerformed(ActionEvent e) {
        final Command command = createCommand();
        command.setDescription(commandName);
        TextureFlow.getCommandQueue().doCommand(command);
    }

    protected abstract Command createCommand();
}
