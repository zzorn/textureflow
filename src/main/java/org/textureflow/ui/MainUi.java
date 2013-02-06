package org.textureflow.ui;

import org.textureflow.TextureFlow;
import org.textureflow.model.Project;
import org.textureflow.utils.AbstractCommand;
import org.textureflow.utils.Command;
import org.textureflow.utils.CommandAction;
import org.textureflow.utils.SimpleFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 *
 */
public class MainUi {

    private Project project;

    private SimpleFrame frame;

    public void startMainUi() {
    }

    private JMenuBar createMenuBar() {
        final JMenuBar menuBar = new JMenuBar();

        final JMenu fileMenu = new JMenu("File");
        fileMenu.add(TextureFlow.EXIT_ACTION);
        menuBar.add(fileMenu);

        return menuBar;
    }

    public SimpleFrame getFrame() {
        return frame;
    }

    private JComponent createMainPanel() {
        final JPanel panel = new JPanel();



        return panel;
    }

}
