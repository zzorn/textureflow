package org.textureflow.model.part;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 *
 */
public class PictureRenderer implements TexturePartRenderer {

    private BufferedImage image;

    public PictureRenderer(BufferedImage image) {
        this.image = image;
    }

    @Override
    public TexturePartRenderer copy() {
        return new PictureRenderer(image);
    }

    @Override
    public void render(Graphics2D g, long randomSeed, int x, int y, int w, int h) {
        g.drawImage(image, x, y, w, h, null);
    }
}
