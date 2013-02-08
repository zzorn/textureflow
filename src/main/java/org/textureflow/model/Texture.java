package org.textureflow.model;


import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;
import org.apache.pivot.collections.ListListener;
import org.apache.pivot.collections.Sequence;
import org.apache.pivot.wtk.MeterListener;
import org.apache.pivot.wtk.WTKListenerList;

import java.awt.*;
import java.awt.event.TextListener;
import java.awt.image.BufferedImage;

/**
 *
 */
public class Texture {

    private final List<Effect> effects = new ArrayList<Effect>();

    private BufferedImage image;

    private int width = 256;
    private int height = 256;

    private TextureListenerList textureListeners = new TextureListenerList();


    public Texture() {
        this(256, 256);
    }

    public Texture(int width, int height) {
        this.width = width;
        this.height = height;
        effects.add(new FillColorEffect("Black", Color.BLACK));

        effects.getListListeners().add(new ListListener.Adapter<Effect>() {
            @Override
            public void itemInserted(List<Effect> list, int index) {
                textureListeners.onTextureContentsChanged();

                // TODO: Listen to changes to the effect, update texture when changed
            }

            @Override
            public void itemsRemoved(List<Effect> list, int index, Sequence<Effect> items) {
                textureListeners.onTextureContentsChanged();

                // TODO: Stop listening to changes to effect
            }

            @Override
            public void itemUpdated(List<Effect> list, int index, Effect previousItem) {
                textureListeners.onTextureContentsChanged();

                // TODO: Stop listening to changes to effect
                // TODO: Listen to changes to the effect, update texture when changed
            }

            @Override
            public void listCleared(List<Effect> list) {
                textureListeners.onTextureContentsChanged();

                // TODO: Stop listening to changes to effects, if possible?
            }
        });
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


    public BufferedImage getImage() {
        if (image == null) {
            image = createImage();
        }
        else {
            renderTexture(image.createGraphics(), 0, 0, width, height);
        }
        return image;
    }


    public BufferedImage createImage() {
        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        renderTexture(bufferedImage.createGraphics(), 0, 0, width, height);
        return bufferedImage;
    }

    public void renderTexture(Graphics2D g) {
        renderTexture(g, 0, 0, width, height);
    }

    public void renderTexture(Graphics2D g, int x, int y, int w, int h) {
        final Shape oldClip = g.getClip();
        g.setClip(x, y, w, h);

        clearToTransparent(g, x, y, w, h);

        for (Effect effect : effects) {
            effect.render(g, x, y, w, h);
        }

        g.setClip(oldClip);
    }

    private void clearToTransparent(Graphics2D g, int x, int y, int w, int h) {
        final Composite oldComposite = g.getComposite();
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));

        g.fillRect(x, y, w, h);

        g.setComposite(oldComposite);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public TextureListenerList getTextureListeners() {
        return textureListeners;
    }

    public static class TextureListenerList extends WTKListenerList<TextureListener> implements TextureListener {
        @Override
        public void onTextureContentsChanged() {
            for (TextureListener listener : this) {
                listener.onTextureContentsChanged();
            }
        }

        @Override
        public void onTextureSizeChanged(int w, int h) {
            for (TextureListener listener : this) {
                listener.onTextureSizeChanged(w, h);
            }
        }
    }

}
