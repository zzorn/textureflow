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
    public void render(Graphics2D g, int w, int h) {
        random.setSeed(randomSeed);

        for (int i = 0; i < num; i++) {
            final long seed = random.nextLong();
            final int size = minSize + random.nextInt(maxSize - minSize);
            final int sizeX = size;
            final int sizeY = size;
            final int x1 = random.nextInt(w + sizeX) - sizeX;
            final int y1 = random.nextInt(h + sizeY) - sizeY;
            final int x2 = x1 + sizeX;
            final int y2 = y1 + sizeY;

            // Render
            if (x1 < 0 && y1 < 0) texturePartRenderer.render(g, seed, x1 + w, y1 + h, sizeX, sizeY);
            if (x1 < 0) texturePartRenderer.render(g, seed, x1 + w, y1, sizeX, sizeY);
            if (y1 < 0) texturePartRenderer.render(g, seed, x1, y1 + h, sizeX, sizeY);
            texturePartRenderer.render(g, seed, x1, y1, sizeX, sizeY);
            if (x2 > w) texturePartRenderer.render(g, seed, x1 - w, y1, sizeX, sizeY);
            if (y2 > h) texturePartRenderer.render(g, seed, x1, y1 - h, sizeX, sizeY);
            if (x2 > w && y2 > h) texturePartRenderer.render(g, seed, x1 - h, y1 - h, sizeX, sizeY);
        }
    }
}
