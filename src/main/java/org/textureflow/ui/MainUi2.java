package org.textureflow.ui;

import org.apache.pivot.wtk.*;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.Window;

import java.awt.*;


/**
 *
 */
public class MainUi2 {

    private Window mainWindow;

    public void openMainUi(Display display) {
        mainWindow = new Window();

        Label label = new Label();
        label.setText("Hello World!");
        label.getStyles().put("font", new Font("Arial", Font.BOLD, 24));
        label.getStyles().put("color", Color.BLUE);
        label.getStyles().put("horizontalAlignment", HorizontalAlignment.CENTER);
        label.getStyles().put("verticalAlignment", VerticalAlignment.CENTER);

        mainWindow.setContent(label);
        mainWindow.setTitle("Hello World!");
        mainWindow.setMaximized(true);

        mainWindow.open(display);

    }

    public void closeMainUi() {

    }

}
