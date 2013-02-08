package org.textureflow.model;

import org.textureflow.model.part.CirclePartRenderer;
import org.textureflow.model.part.TexturePartRenderer;

import java.awt.*;
import java.util.Random;

/**
 *
 */
public class PartEffect extends Effect {

    private TexturePartRenderer texturePartRenderer = new CirclePartRenderer(Color.GRAY);
    private int num = 50;
    private long randomSeed = 42;
    private Random random = new Random();
    private int minSize = 10;
    private int maxSize = 20;

    public PartEffect(String name, TexturePartRenderer texturePartRenderer, int num, int minSize, int maxSize) {
        super(name);
        this.texturePartRenderer = texturePartRenderer;
        this.num = num;
        this.minSize = minSize;
        this.maxSize = maxSize;

        randomSeed = random.nextLong();
    }

    @Override
    protected Effect copyData() {
        return new PartEffect(getName(), texturePartRenderer.copy(), num, minSize, maxSize);
    }

    @Override
    public void render(Graphics2D g, int x, int y, int w, int h) {
        random.setSeed(randomSeed);

        for (int i = 0; i < num; i++) {
            final long seed = random.nextLong();
            final int size = minSize + random.nextInt(maxSize - minSize);
            final int partSizeX = size;
            final int partSizeY = size;
            final int x1 = x + random.nextInt(w + partSizeX) - partSizeX;
            final int y1 = y + random.nextInt(h + partSizeY) - partSizeY;

            // Render
            for (int tx = -1; tx <= 1; tx++) {
                for (int ty = -1; ty <= 1; ty++) {
                    final int xPos = x1 + w * tx;
                    final int yPos = y1 + h * ty;

                    if (isInside(xPos, yPos, partSizeX, partSizeY, x, y, w, h)) {
                        texturePartRenderer.render(g, seed, xPos, yPos, partSizeX, partSizeY);
                    }
                }
            }
        }
    }

    private boolean isInside(int xPos, int yPos, int partSizeX, int partSizeY, int x, int y, int w, int h) {
        return xPos + partSizeX >= x && xPos <  x + w &&
               yPos + partSizeY >= y && yPos <  y + h;
    }
}
