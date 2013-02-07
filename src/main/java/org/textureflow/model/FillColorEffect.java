package org.textureflow.model;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Fills texture with a flat color.
 */
public class FillColorEffect extends Effect {

    private Color color = Color.GRAY;

    public FillColorEffect(Color color) {
        this.color = color;
    }

    public FillColorEffect(String name, Color color) {
        super(name);
        this.color = color;
    }

    @Override
    protected Effect copyData() {
        return new FillColorEffect(color);
    }

    @Override
    public void render(Graphics2D g, int w, int h) {
        g.setColor(color);
        g.fillRect(0,0,w,h);
    }
}
