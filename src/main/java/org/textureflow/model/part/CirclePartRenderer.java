package org.textureflow.model.part;

import java.awt.*;

/**
 *
 */
public class CirclePartRenderer implements TexturePartRenderer {

    private Color color = Color.GRAY;

    public CirclePartRenderer(Color color) {
        this.color = color;
    }

    @Override
    public TexturePartRenderer copy() {
        return new CirclePartRenderer(color);
    }

    @Override
    public void render(Graphics2D g, long randomSeed, int x, int y, int w, int h) {
        g.setColor(color);

        g.fillOval(x, y, w, h);
    }
}
