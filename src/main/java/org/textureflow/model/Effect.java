package org.textureflow.model;

import org.apache.pivot.wtk.content.ListItem;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 */
// Grrr, why can't frameworks support extendability by using interfaces?  It would have been very easy to have ListItem be an interface with a default implementation...
public abstract class Effect extends ListItem {

    protected Effect() {
    }

    protected Effect(String name) {
        super(name);
    }

    /**
     * @return a new copy of this effect.
     */
    public final Effect copy() {
        Effect effect = copyData();
        effect.setName(getName());
        return effect;
    }

    /**
     * @return name of the effect
     */
    public String getName() {
        return getText();
    }

    public void setName(String name) {
        setText(name);
    }

    protected abstract Effect copyData();

    @Override
    public final Object getUserData() {
        return this;
    }

    @Override
    public final void setUserData(Object userData) {
        throw new UnsupportedOperationException("Not supported");
    }

    public abstract void render(Graphics2D g, int w, int h);
}
