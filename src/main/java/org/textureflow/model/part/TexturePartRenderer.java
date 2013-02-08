package org.textureflow.model.part;

import java.awt.*;

/**
 *
 */
public interface TexturePartRenderer {

    TexturePartRenderer copy();

    void render(Graphics2D g, long randomSeed, int x, int y, int w, int h);

}
