package org.textureflow.model;


import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;

import java.awt.*;

/**
 *
 */
public class Texture {

    private List<Effect> effects = new ArrayList<Effect>();


    public Texture() {
        effects.add(new FillColorEffect("Black", Color.BLACK));
    }

    public void addEffect(Effect effect) {
        effects.add(effect);
    }

    public void addEffect(Effect effect, int index) {
        effects.insert(effect, index);
    }

    public List<Effect> getEffects() {
        return effects;
    }
}
