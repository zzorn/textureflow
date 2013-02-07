package org.textureflow.model;

import java.awt.*;

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

}
