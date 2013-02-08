package org.textureflow.model;

import java.awt.*;
import java.util.Random;

/**
 *
 */
public class BubbleEffect extends Effect {

    private Color color = Color.cyan;
    private long randSeed = 42;
    private int num = 10;
    private int minSize = 10;
    private int maxSize = 20;

    private Random random = new Random();

    public BubbleEffect(String name, Color color, int num, int minSize, int maxSize) {
        super(name);
        this.color = color;
        this.num = num;
        this.minSize = minSize;
        this.maxSize = maxSize;

        randSeed = random.nextLong();
    }

    @Override
    protected Effect copyData() {
        return new BubbleEffect(getName(), color, num, minSize, maxSize);
    }

    @Override
    public void render(Graphics2D g, int w, int h) {
        random.setSeed(randSeed);

        g.setColor(color);

        for (int i = 0; i < num; i++) {
            final int bubbleW = minSize + random.nextInt(maxSize - minSize);
            final int bubbleH = minSize + random.nextInt(maxSize - minSize);
            g.drawOval(
                    random.nextInt(w) - bubbleW/2,
                    random.nextInt(h) - bubbleH/2,
                    bubbleW,
                    bubbleH);
        }


    }
}
