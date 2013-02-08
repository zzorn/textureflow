package org.textureflow;

import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.collections.Map;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.DesktopApplicationContext;
import org.apache.pivot.wtk.Display;
import org.textureflow.model.*;
import org.textureflow.model.part.CirclePartRenderer;
import org.textureflow.model.part.PictureRenderer;
import org.textureflow.ui.MainUi;
import org.textureflow.ui.MainUi2;
import org.textureflow.utils.AbstractCommand;
import org.textureflow.utils.Command;
import org.textureflow.utils.CommandAction;
import org.textureflow.utils.CommandQueue;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 *
 */
public class TextureFlow implements Application  {

    private static Project currentProject = new Project();
    private static final CommandQueue commandQueue = new CommandQueue();
    private static Library basicLibrary = createBasicLibrary();
    private static MainUi2 mainUi2 = new MainUi2(basicLibrary);

    public static final CommandAction EXIT_ACTION = new CommandAction("Exit") {
        @Override
        protected Command createCommand() {
            return new AbstractCommand() {
                @Override
                public boolean execute(Project project) {
                    if (currentProject != null && currentProject.hasUnsavedChanges()) {
                        // TODO: Replace with pivot thingy
                        final int result = JOptionPane.showConfirmDialog(
                                null,
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
        /*
        commandQueue.setProject(currentProject);

        mainUi = new MainUi();
        mainUi.startMainUi();
        */

        DesktopApplicationContext.main(TextureFlow.class, args);
    }


    /**
     * Called when the application is starting up.
     *
     * @param display
     * The display on which this application was started.
     *
     * @param properties
     * Initialization properties passed to the application.
     */
    @Override
    public void startup(Display display, Map<String, String> properties) throws Exception {
        mainUi2.openMainUi(display);
        mainUi2.showProject(currentProject);
    }

    /**
     * Called when the application is being shut down.
     *
     * @param optional
     * If <tt>true</tt>, the shutdown may be cancelled by returning a value of
     * <tt>true</tt>.
     *
     * @return
     * <tt>true</tt> to cancel shutdown, <tt>false</tt> to continue.
     */
    @Override
    public boolean shutdown(boolean optional) throws Exception {
        mainUi2.closeMainUi();

        return false;
    }

    /**
     * Called to notify the application that it is being suspended.
     */
    @Override
    public void suspend() throws Exception {
    }

    /**
     * Called when a suspended application has been resumed.
     */
    @Override
    public void resume() throws Exception {
    }


    private static Library createBasicLibrary() {
        Library library = new Library();

        library.addEntry(new FillColorEffect("Shadow", new Color(0, 0, 0, 0.33f)));
        library.addEntry(new FillColorEffect("Blue", Color.BLUE));
        library.addEntry(new PartEffect("Grey blobs", new CirclePartRenderer(Color.GRAY), 40, 20, 30));
        library.addEntry(new PartEffect("Blobs", new PictureRenderer(loadImage("testpic1.png")), 20, 20, 50));
        library.addEntry(new PartEffect("Suns", new PictureRenderer(loadImage("sun.png")), 60, 10, 30));

        return library;
    }

    private static BufferedImage loadImage(String name) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("assets/" + name));
        } catch (IOException e) {
            System.out.println("Problem loading file" + e.getMessage());
        }
        return img;
    }

}
