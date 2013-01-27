package org.textureflow.model;

import org.textureflow.utils.AbstractCommand;
import org.textureflow.utils.Command;
import org.textureflow.utils.CommandQueue;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Project {

    private final List<Texture> textures = new ArrayList<Texture>();

    private boolean changesMade = false;

    public Project() {
    }

    public Command newTextureCommand() {
        return new AbstractCommand("Create New Texture") {
            @Override
            public boolean execute(Project project) {



                return true;
            }
        };
    }

    public boolean hasUnsavedChanges() {
        return changesMade;
    }

}
